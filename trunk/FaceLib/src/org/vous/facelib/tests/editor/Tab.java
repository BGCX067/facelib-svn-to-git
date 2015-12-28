package org.vous.facelib.tests.editor;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.vous.facelib.bitmap.Bitmap;
import org.vous.facelib.tests.util.FramePanel;


public class Tab extends JPanel
{
	public static final long serialVersionUID = 1L;

	private FramePanel mFramePanel;
	private String mFilename;
	private String mFormattedFilename;
	private Undo mUndo;

	private boolean mIsLocked;
	private TabbedPane mOwner;

	private FilterExecutor mExecutor;

	public Tab(Bitmap frame, String filename)
	{
		mFramePanel = new FramePanel();

		mFramePanel.setPreferredSize(new Dimension(frame.getWidth(), frame
		        .getHeight()));

		setSize(new Dimension(frame.getWidth(), frame.getHeight()));

		mFramePanel.updateFrame(frame);
		mFilename = filename;
		mFormattedFilename = formatFilename(filename);
		mUndo = new Undo(this);
		mIsLocked = false;
		mOwner = null;
		mExecutor = new FilterExecutor(this);

		add(new JScrollPane(mFramePanel));
	}

	public void setOwner(TabbedPane owner)
	{
		mOwner = owner;
	}

	public TabbedPane getOwner()
	{
		return mOwner;
	}

	public Bitmap getCurrentFrame()
	{
		return mFramePanel.getFrame();
	}

	public void updateFrame(Bitmap frame)
	{
		mFramePanel.updateFrame(frame);
	}

	public String getFilename()
	{
		return mFilename;
	}

	public String getFormattedFilename()
	{
		return mFormattedFilename;
	}

	private String formatFilename(String name)
	{
		String formatted = new String();

		if (name.length() > 16)
		{
			formatted = name.substring(0, 6);
			formatted += "...";
			formatted += name.substring(name.length() - 7, name.length());

		} else
			formatted = name;

		return formatted;
	}

	public void setStatusText(String text)
	{

	}

	public Undo getUndo()
	{
		return mUndo;
	}

	public void lock()
	{
		if (!isLocked())
		{
			mIsLocked = true;
			mOwner.fireTabLocked(this);
		}
	}

	public void unlock()
	{
		if (isLocked())
		{
			mIsLocked = false;
			mOwner.fireTabUnlocked(this);
		}
	}

	public boolean isLocked()
	{
		return mIsLocked;
	}

	public FilterExecutor getFilterExecutor()
	{
		return mExecutor;
	}
}
