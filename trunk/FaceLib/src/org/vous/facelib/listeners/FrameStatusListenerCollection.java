package org.vous.facelib.listeners;

public class FrameStatusListenerCollection extends
        ListenerCollection<IFrameStatusListener>
{
	public void fireConnected()
	{
		for (IFrameStatusListener listener : super.getCollection())
		{
			listener.connected();
		}
	}

	public void fireDisconnected()
	{
		for (IFrameStatusListener listener : super.getCollection())
		{
			listener.disconnected();
		}
	}

	public void fireExceptionOccured(Exception e, boolean fatal)
	{
		for (IFrameStatusListener listener : super.getCollection())
		{
			listener.exceptionOccured(e, fatal);
		}
	}
}
