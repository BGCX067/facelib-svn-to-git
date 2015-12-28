package org.vous.facelib.motion;

import org.vous.facelib.bitmap.Bitmap;
import org.vous.facelib.listeners.IFrameListener;
import org.vous.facelib.listeners.MotionListenerCollection;


public class MotionDetector implements IFrameListener
{
	private MotionListenerCollection mListeners;

	public MotionDetector()
	{
		mListeners = new MotionListenerCollection();
	}

	public MotionListenerCollection getListeners()
	{
		return mListeners;
	}

	@Override
	public void frameUpdated(Bitmap frame)
	{

	}
}
