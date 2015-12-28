package org.vous.facelib.listeners;

public interface IFrameStatusListener
{
	public void connected();

	public void disconnected();

	public void exceptionOccured(Exception e, boolean fatal);
}
