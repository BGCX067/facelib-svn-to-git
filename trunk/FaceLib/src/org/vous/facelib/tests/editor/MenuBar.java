package org.vous.facelib.tests.editor;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuBar implements ITabListener
{
	private JMenuBar mMenuBar;
	private ActionListener mListener;
	private JMenu mFileMenu;
	private JMenu mEditMenu;
	private JMenu mFilterMenu;

	private JMenuItem mFileOpen;
	private JMenuItem mFileCloseCurrent;
	private JMenuItem mFileCloseAll;
	private JMenuItem mFileSave;
	private JMenuItem mFileExit;
	private JMenuItem mEditUndo;
	private JMenuItem mFilterGray;
	private JMenuItem mFilterThreshold;
	private JMenuItem mFilterMedian;
	private JMenuItem mFilterMirror;
	private JMenuItem mFilterBorder;
	private JMenuItem mFilterInvert; 
	private JMenuItem mFilterInvertAlpha;
	private JMenuItem mFilterText;
	private JMenuItem mFilterTri;
	private JMenuItem mFilterFlip;
	private JMenuItem mFilterGain;
	private JMenuItem mFilterPixellate;

	public MenuBar(ActionListener listener)
	{
		mMenuBar = new JMenuBar();
		mListener = listener;

		mFileOpen = new JMenuItem("Open Image...");
		mFileSave = new JMenuItem("Save as...");
		mFileCloseCurrent = new JMenuItem("Close Tab");
		mFileCloseAll = new JMenuItem("Close All Tabs");
		mFileExit = new JMenuItem("Exit");

		mEditUndo = new JMenuItem("Undo");

		mFilterGray = new JMenuItem("Grayscale");
		mFilterThreshold = new JMenuItem("Threshold");
		mFilterMedian = new JMenuItem("Median");
		mFilterMirror = new JMenuItem("Mirror");
		mFilterBorder = new JMenuItem("Draw Border");
		mFilterInvert = new JMenuItem("Invert RGB");
		mFilterInvertAlpha = new JMenuItem("Invert alpha"); 
		mFilterText = new JMenuItem("Draw Text");
		mFilterTri = new JMenuItem("Tri-Tone");
		mFilterFlip = new JMenuItem("Flip");
		mFilterGain = new JMenuItem("Gain");
		mFilterPixellate = new JMenuItem("Pixellate");

		build();

		disableEditing();
		tabCountChanged(0);
	}

	private void build()
	{
		int shortcutKey = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

		mFileMenu = new JMenu("File");
		mFileMenu.setMnemonic(KeyEvent.VK_F);
		mMenuBar.add(mFileMenu);

		mEditMenu = new JMenu("Edit");
		mEditMenu.setMnemonic(KeyEvent.VK_E);
		mMenuBar.add(mEditMenu);

		mFilterMenu = new JMenu("Filter");
		mFilterMenu.setMnemonic(KeyEvent.VK_I);
		mMenuBar.add(mFilterMenu);

		mFileOpen.setName("file.open");
		mFileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
		        shortcutKey));
		mFileOpen.addActionListener(mListener);
		mFileMenu.add(mFileOpen);

		mFileSave.setName("file.save");
		mFileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
		        shortcutKey));
		mFileSave.addActionListener(mListener);
		mFileMenu.add(mFileSave);

		mFileMenu.addSeparator();

		mFileCloseCurrent.setName("file.closeCurrent");
		mFileCloseCurrent.addActionListener(mListener);
		mFileCloseCurrent.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
		        shortcutKey));
		mFileMenu.add(mFileCloseCurrent);

		mFileCloseAll.setName("file.closeAll");
		mFileCloseAll.addActionListener(mListener);
		mFileCloseAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,
		        shortcutKey));
		mFileMenu.add(mFileCloseAll);
		mFileMenu.addSeparator();

		mFileExit.setName("file.exit");
		mFileExit.addActionListener(mListener);
		mFileExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
		        shortcutKey));
		mFileMenu.add(mFileExit);

		mEditUndo.setName("edit.undo");
		mEditUndo.addActionListener(mListener);
		mEditUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
		        shortcutKey));
		mEditMenu.add(mEditUndo);
		
		mFilterBorder.setName("filter.border");
		mFilterBorder.addActionListener(mListener);
		mFilterBorder.setMnemonic(KeyEvent.VK_B);
		mFilterMenu.add(mFilterBorder);

		mFilterGray.setName("filter.gray");
		mFilterGray.addActionListener(mListener);
		mFilterGray.setMnemonic(KeyEvent.VK_G);
		mFilterMenu.add(mFilterGray);

		mFilterThreshold.setName("filter.threshold");
		mFilterThreshold.addActionListener(mListener);
		mFilterThreshold.setMnemonic(KeyEvent.VK_T);
		mFilterMenu.add(mFilterThreshold);
				
		mFilterMedian.setName("filter.median");
		mFilterMedian.addActionListener(mListener);
		mFilterMedian.setMnemonic(KeyEvent.VK_M);
		mFilterMenu.add(mFilterMedian);
		
		mFilterInvert.setName("filter.invert");
		mFilterInvert.addActionListener(mListener);
		mFilterInvert.setMnemonic(KeyEvent.VK_V);
		mFilterMenu.add(mFilterInvert);
		
		mFilterInvertAlpha.setName("filter.invertalpha");
		mFilterInvertAlpha.addActionListener(mListener);
		mFilterInvertAlpha.setMnemonic(KeyEvent.VK_A);
		mFilterMenu.add(mFilterInvertAlpha);
		
		mFilterTri.setName("filter.tritone");
		mFilterTri.addActionListener(mListener);
		mFilterTri.setMnemonic(KeyEvent.VK_R);
		mFilterMenu.add(mFilterTri);
		
		mFilterGain.setName("filter.gain");
		mFilterGain.addActionListener(mListener);
		mFilterMenu.add(mFilterGain);
		
		mFilterText.setName("filter.text");
		mFilterText.addActionListener(mListener);
		mFilterText.setMnemonic(KeyEvent.VK_E);
		mFilterMenu.add(mFilterText);
		
		mFilterFlip.setName("filter.flip");
		mFilterFlip.addActionListener(mListener);
		mFilterFlip.setMnemonic(KeyEvent.VK_F);
		mFilterMenu.add(mFilterFlip);
		
		mFilterMirror.setName("filter.mirror");
		mFilterMirror.addActionListener(mListener);
		mFilterMirror.setMnemonic(KeyEvent.VK_I);
		mFilterMenu.add(mFilterMirror);
		
		mFilterPixellate.setName("filter.pixellate");
		mFilterPixellate.addActionListener(mListener);
		mFilterMenu.add(mFilterPixellate);
	}

	public JMenuBar getComponent()
	{
		return mMenuBar;
	}

	private void enableEditing()
	{
		mFilterMenu.setEnabled(true);
		mEditMenu.setEnabled(true);
	}

	private void disableEditing()
	{
		mFilterMenu.setEnabled(false);
		mEditMenu.setEnabled(false);
	}

	@Override
	public void tabCountChanged(int tabCount)
	{
		boolean showCloseAll = !(tabCount < 2);
		boolean showClose = (tabCount > 0);

		mFileCloseAll.setEnabled(showCloseAll);
		mFileCloseCurrent.setEnabled(showClose);
		mFileSave.setEnabled(showClose);

		if (tabCount > 0)
			enableEditing();

		else
			disableEditing();
	}

	@Override
	public void tabSelected(Tab tab)
	{
		if (tab.isLocked())
			disableEditing();

		else
			enableEditing();

		if (tab.getUndo().getLevelCount() > 0)
			mEditUndo.setEnabled(true);

		else
			mEditUndo.setEnabled(false);
	}

	@Override
	public void tabLocked(Tab tab)
	{
		disableEditing();
	}

	@Override
	public void tabUnlocked(Tab tab)
	{
		enableEditing();
	}

	@Override
	public void tabUndoLevelChanged(Tab tab, int level)
	{
		if (level > 0)
			mEditUndo.setEnabled(true);

		else
			mEditUndo.setEnabled(false);
	}
}
