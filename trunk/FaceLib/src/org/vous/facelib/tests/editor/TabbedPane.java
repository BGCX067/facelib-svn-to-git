package org.vous.facelib.tests.editor;

import java.awt.Dimension;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.vous.facelib.bitmap.Bitmap;
import org.vous.facelib.tests.util.IExitListener;


public class TabbedPane extends TabListenerCollection implements ChangeListener, IExitListener
{
	private JTabbedPane mTabs;
	public final static long serialVersionUID = 0;
	private int mLastTabCount = 0;

	public TabbedPane()
	{
		mTabs = new JTabbedPane();
		mTabs.setPreferredSize(new Dimension(800, 600));
		mTabs.addChangeListener(this);

		mTabs.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}

	public void addTab(Tab tab)
	{
		Bitmap scaled = tab.getCurrentFrame().scale(16, 16);
		ImageIcon icon = new ImageIcon(scaled.getBackingImage());

		mTabs.insertTab(tab.getFormattedFilename(), icon, tab, tab
		        .getFilename(), mTabs.getSelectedIndex() + 1);

		mTabs.setSelectedComponent(tab);
		tab.setOwner(this);
	}

	public void closeCurrentTab()
	{
		if (mTabs.getTabCount() > 0)
		{
			mTabs.removeTabAt(mTabs.getSelectedIndex());
		}
	}

	public void closeAllTabs()
	{
		if (mTabs.getTabCount() > 0)
		{
			for (int i = 0; i <= mTabs.getTabCount(); i++)
			{
				mTabs.removeTabAt(mTabs.getSelectedIndex());
			}
		}
	}

	public int getTabCount()
	{
		return mTabs.getTabCount();
	}

	public JTabbedPane getComponent()
	{
		return mTabs;
	}

	public void stateChanged(ChangeEvent event)
	{
		int tabCount = mTabs.getTabCount();

		if (tabCount != 0)
			fireTabSelected((Tab) mTabs.getSelectedComponent());

		if (tabCount != mLastTabCount)
			fireTabCountChanged(mTabs.getTabCount());

		mLastTabCount = tabCount;
	}

	public void repaint()
	{
		mTabs.repaint();
	}

	@Override
    public void appExiting()
	{
		try {
		// save history
		new File("c:\\2.txt").createNewFile(); } catch (Exception e) { };
    }
}
