package org.vous.facelib.sources;

import org.vous.facelib.bitmap.Bitmap;
import org.vous.facelib.threading.Worker;


public class DirectorySourceReader extends AbstractFrameReader implements
        Runnable
{
	private Bitmap[] mFrames;
	private DirectorySource mSource;
	private Worker mWorker;

	protected DirectorySourceReader(DirectorySource source)
	{
		mSource = source;
		mFrames = source.getFrames();

		mWorker = new Worker(this, true);
		mSource.getStatusListeners().fireConnected();
	}

	public void disconnect() throws Exception
	{
		mWorker.stop();
		mSource.getStatusListeners().fireDisconnected();
	}

	public void run()
	{
		int length = mFrames.length - 1;
		int index = 0;
		long interval = 1000 / (long) mSource.getFps();

		while (!Thread.interrupted() && !mWorker.shouldStop())
		{
			if (index < length)
			{
				mSource.getFrameListeners().fireFrameUpdate(mFrames[index]);
				index++;

				try
				{
					Thread.sleep(interval);
				} catch (InterruptedException ie)
				{
				}
				;
			}

			else
				index = 0;
		}

	}

}
