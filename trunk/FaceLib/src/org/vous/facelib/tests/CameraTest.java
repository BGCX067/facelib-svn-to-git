package org.vous.facelib.tests;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.CaptureDeviceInfo;
import javax.media.format.RGBFormat;

import org.vous.facelib.bitmap.Bitmap;
import org.vous.facelib.listeners.IFrameListener;
import org.vous.facelib.listeners.IFrameStatusListener;
import org.vous.facelib.sources.CameraSource;
import org.vous.facelib.sources.CameraSourceReader;
import org.vous.facelib.sources.util.Camera;
import org.vous.facelib.tests.util.FramePanel;
import org.vous.facelib.tests.util.TestApp;


public class CameraTest implements IFrameListener, IFrameStatusListener
{
	private FramePanel mPanel;

	public static void main(String[] args) throws Exception
	{
		new CameraTest().demo();
	}

	public void demo() throws Exception
	{
		CaptureDeviceInfo devInfo = Camera.getDevices()[0];
		RGBFormat format = Camera.getHighestResolutionFormat(devInfo);

		CameraSource source = new CameraSource(devInfo, format, 25);
		source.getFrameListeners().addListener(this);
		source.getStatusListeners().addListener(this);

		final CameraSourceReader reader = source.connect();

		TestApp app = new TestApp("");
		mPanel = new FramePanel();
		app.getContentPane().add(mPanel);

		app.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent event)
			{
				try
				{
					reader.disconnect();
				} catch (Exception e)
				{

				}
			}
		});
		app.setVisible(true);
	}

	@Override
	public void frameUpdated(Bitmap frame)
	{
		mPanel.updateFrame(frame);
	}

	@Override
	public void connected()
	{
		System.out.println("Connected");
	}

	@Override
	public void disconnected()
	{
		System.out.println("Disconnected");
	}

	@Override
	public void exceptionOccured(Exception e, boolean fatal)
	{
		e.printStackTrace();
	}

}
