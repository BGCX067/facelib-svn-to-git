package org.vous.facelib.listeners;

import org.vous.facelib.bitmap.Bitmap;

public class FrameListenerCollection extends ListenerCollection<IFrameListener>
{
	public void fireFrameUpdate(Bitmap frame)
	{
		for (IFrameListener listener : super.getCollection())
		{
			listener.frameUpdated(frame);
		}
	}
}
