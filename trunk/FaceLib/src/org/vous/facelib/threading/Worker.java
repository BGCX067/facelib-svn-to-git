package org.vous.facelib.threading;

public class Worker
{
	private Thread mThread;
	private boolean mShouldStop;

	public Worker(Runnable task, boolean daemon)
	{
		mThread = new Thread(task);
		mThread.setDaemon(daemon);
		mThread.start();
		mShouldStop = false;
	}

	public void stop()
	{
		mShouldStop = true;
		mThread.interrupt();

		try
		{
			mThread.join();
		}

		catch (InterruptedException ie)
		{

		}

	}

	public boolean shouldStop()
	{
		return mShouldStop;
	}

}
