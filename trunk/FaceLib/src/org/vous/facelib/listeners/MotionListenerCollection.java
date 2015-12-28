package org.vous.facelib.listeners;

import java.awt.Rectangle;

public class MotionListenerCollection extends
        ListenerCollection<IMotionListener>
{
	public void fireMotionDetected(Rectangle[] regions)
	{
		for (IMotionListener listener : super.getCollection())
		{
			listener.motionDetected(regions);
		}
	}
}
