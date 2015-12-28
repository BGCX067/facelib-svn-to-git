package org.vous.facelib.tests.util;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import org.vous.facelib.bitmap.Bitmap;


public class FramePanel extends JPanel
{
	public final static long serialVersionUID = 0;
	private Bitmap mFrame;

	public FramePanel()
	{
		mFrame = null;
	}

	public void updateFrame(Bitmap frame)
	{
		setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
		mFrame = frame;
		repaint();
	}

	public Bitmap getFrame()
	{
		return mFrame;
	}

	public void paintComponent(Graphics g)
	{
		if (mFrame != null)
			g.drawImage(mFrame.getBackingImage(), 0, 0, mFrame.getWidth(),
			        mFrame.getHeight(), null);
	}
}
