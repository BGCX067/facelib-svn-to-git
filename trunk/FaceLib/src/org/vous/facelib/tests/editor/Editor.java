package org.vous.facelib.tests.editor;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
import org.vous.facelib.bitmap.Bitmap;
import org.vous.facelib.filters.BorderFilter;
import org.vous.facelib.filters.FlipFilter;
import org.vous.facelib.filters.GainFilter;
import org.vous.facelib.filters.GrayscaleFilter;
import org.vous.facelib.filters.IFilter;
import org.vous.facelib.filters.InvertAlphaFilter;
import org.vous.facelib.filters.InvertFilter;
import org.vous.facelib.filters.MedianFilter;
import org.vous.facelib.filters.MirrorFilter;
import org.vous.facelib.filters.PixellationFilter;
import org.vous.facelib.filters.TextFilter;
import org.vous.facelib.filters.ThresholdFilter;
import org.vous.facelib.filters.TriToneFilter;
import org.vous.facelib.tests.util.TestApp;


public class Editor implements ITabListener
{
	private TabbedPane mTabs;
	private TestApp mApp;
	private DialogFilter mDialogFilter;
	private ActionListener mMenuListener;
	private MenuBar mMenu;
	private Tab mCurrentTab;

	public static void main(String[] args)
	{
		new Editor();
	}

	public Editor()
	{
		mApp = new TestApp("Ph0t0sh0p");

		mMenuListener = createMenuListener();
		mMenu = new MenuBar(mMenuListener);

		mTabs = new TabbedPane();
		mTabs.addListener(this);
		mTabs.addListener(mMenu);

		mDialogFilter = new DialogFilter();
		mCurrentTab = null;

		mApp.setSize(800, 600);
		mApp.setJMenuBar(mMenu.getComponent());
		mTabs.getComponent().setBorder(BorderFactory.createEtchedBorder());
		mApp.getContentPane().add(mTabs.getComponent());

		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				mApp.pack();
				mApp.setVisible(true);

				try
				{
					mApp.applyLnF(new SubstanceBusinessBlackSteelLookAndFeel());
				}

				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	private ActionListener createMenuListener()
	{
		ActionListener listener = new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				JMenuItem source = (JMenuItem) event.getSource();
				String name = source.getName().toLowerCase();

				if (name.equals("file.open"))
					doFileOpen();

				else if (name.equals("file.save"))
					doFileSave();

				else if (name.equals("file.closecurrent"))
					doFileCloseCurrentTab();

				else if (name.equals("file.closeall"))
					doFileCloseAllTabs();

				else if (name.equals("file.exit"))
					doFileExit();

				else if (name.equals("edit.undo"))
					doEditUndo();

				else if (name.equals("filter.gray"))
					doFilter(new GrayscaleFilter());

				else if (name.equals("filter.threshold"))
					doFilter(new ThresholdFilter(100));

				else if (name.equals("filter.mirror"))
					doFilter(new MirrorFilter());

				else if (name.equals("filter.median"))
					doFilter(new MedianFilter());
				
				else if (name.equals("filter.border"))
					doFilter(new BorderFilter(Color.BLACK, 5));
				
				else if (name.equals("filter.invert"))
					doFilter(new InvertFilter());
				
				else if (name.equals("filter.invertalpha"))
					doFilter(new InvertAlphaFilter());
				
				else if (name.equals("filter.text"))
				{
					Font font = new Font("Courier", Font.PLAIN, 16);
					doFilter(new TextFilter(font, Color.BLACK, "Jacob Jacob Jacob", 20, 20));
				}
				
				else if (name.equals("filter.gain"))
					doFilter(new GainFilter(0.7f));
				
				else if (name.equals("filter.tritone"))
				{
					Color high = Color.YELLOW;
					Color low = Color.CYAN;
					Color mid = Color.GRAY;
					
					doFilter(new TriToneFilter(high, low, mid));
				}
				
				else if (name.equals("filter.flip"))
					doFilter(new FlipFilter());
				
				else if (name.equals("filter.pixellate"))
				{
					int cw = 10, ch = 10;
					doFilter(new PixellationFilter(cw, ch));
				}
				
				
				else
					System.out.println("Unknow source for menu " + name);
			}
		};

		return listener;
	};

	private void doFileOpen()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(mDialogFilter);
		if (fileChooser.showOpenDialog(mApp) != JFileChooser.APPROVE_OPTION)
			return;

		File file = fileChooser.getSelectedFile();
		Bitmap frame = null;

		try
		{
			frame = Bitmap.fromFile(file);
		}

		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Invalid image", "Error",
			        JOptionPane.ERROR_MESSAGE);
		}

		Tab tab = new Tab(frame, file.getName());
		mTabs.addTab(tab);
	}

	private void doFileSave()
	{
		JFileChooser chooser = new JFileChooser(new File(File.separator
		        + ".png"));

		int result = chooser.showSaveDialog(mApp);
		if (result == JFileChooser.APPROVE_OPTION)
		{
			try
			{
				ImageIO.write(getCurrentTab().getCurrentFrame()
				        .getBackingImage(), "jpg", chooser.getSelectedFile());
			}

			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
		}
	}

	private void doFileCloseCurrentTab()
	{
		mTabs.closeCurrentTab();
	}

	private void doFileCloseAllTabs()
	{
		int tabCount = mTabs.getTabCount();

		if (tabCount > 1)
		{
			if (JOptionPane.showConfirmDialog(mApp, "Close all " + tabCount
			        + " tabs at once?", "Close All Tabs?",
			        JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION)
			{
				mTabs.closeAllTabs();
			}
		}

		else
			mTabs.closeCurrentTab();
	}

	private void doFileExit()
	{
		System.exit(0);
	}

	private void doEditUndo()
	{
		Undo undo = mCurrentTab.getUndo();
		Bitmap frame = undo.getPrevLevel();

		if (frame != null)
			mCurrentTab.updateFrame(frame);
	}
	
	private void doFilter(IFilter filter)
	{
		getCurrentTab().getFilterExecutor().execute(filter);
	}

	public void tabCountChanged(int newCount)
	{
		// ignore
	}

	public void tabSelected(Tab tab)
	{
		mCurrentTab = tab;
	}

	@Override
	public void tabLocked(Tab tab)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void tabUnlocked(Tab tab)
	{
		// TODO Auto-generated method stub

	}

	protected Tab getCurrentTab()
	{
		return mCurrentTab;
	}

	@Override
	public void tabUndoLevelChanged(Tab tab, int level)
	{

	}

}
