package org.vous.facelib.sources;

import org.vous.facelib.listeners.FrameListenerCollection;
import org.vous.facelib.listeners.FrameStatusListenerCollection;

public abstract class AbstractFrameSource
{
	private FrameStatusListenerCollection mStatusListeners;
	private FrameListenerCollection mFrameListeners;
	private int mFps;

	public AbstractFrameSource(int fps)
	{
		mStatusListeners = new FrameStatusListenerCollection();
		mFrameListeners = new FrameListenerCollection();
		mFps = fps;
	}

	public int getFps()
	{
		return mFps;
	}

	public FrameStatusListenerCollection getStatusListeners()
	{
		return mStatusListeners;
	}

	public FrameListenerCollection getFrameListeners()
	{
		return mFrameListeners;
	}

	public abstract AbstractFrameReader connect() throws Exception;
}
