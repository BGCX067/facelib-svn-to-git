package org.vous.facelib.sources;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.media.Buffer;
import javax.media.util.BufferToImage;

import org.vous.facelib.bitmap.Bitmap;


public class CameraSourceReader extends AbstractFrameReader implements Runnable
{
	private CameraSource mSource;
	private Thread mThread;
	private boolean mThreadRun = false;
	private Buffer mBuffer;
	private BufferToImage mConverter;

	protected CameraSourceReader(CameraSource source)
	{
		mSource = source;
		mBuffer = new Buffer();
		mConverter = new BufferToImage(source.getFormat());

		mThreadRun = true;
		mThread = new Thread(this);
		mThread.start();

		mSource.getStatusListeners().fireConnected();
	}

	@Override
	public void disconnect() throws Exception
	{
		if (!mThreadRun)
			return;

		mThreadRun = false;
		mThread.interrupt();

		try
		{
			mThread.join();
		} catch (InterruptedException ioe)
		{
			/* ignore */
		}

		/* close open connections now, please */
		try
		{
			mSource.getPushSource().disconnect();
			mSource.getProcessor().close();
			mSource.getDataSource().disconnect();
		} catch (Exception e)
		{
			/* for now, print exception */
			e.printStackTrace();
		}

		mSource.getStatusListeners().fireDisconnected();
	}

	@Override
	public void run()
	{
		while (!Thread.interrupted() && mThreadRun)
		{
			try
			{
				Bitmap frame = readFrame();
				mSource.getFrameListeners().fireFrameUpdate(frame);
			}

			catch (IOException ioe)
			{
				mSource.getStatusListeners().fireExceptionOccured(ioe, false);
			}
		}
	}

	private Bitmap readFrame() throws IOException
	{
		mSource.getPushStream().read(mBuffer);
		return new Bitmap((BufferedImage) mConverter.createImage(mBuffer));
	}

}
