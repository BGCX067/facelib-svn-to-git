package org.vous.facelib.tests;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.vous.facelib.bitmap.Bitmap;
import org.vous.facelib.filters.GrayscaleFilter;
import org.vous.facelib.filters.IFilter;
import org.vous.facelib.listeners.IFrameListener;
import org.vous.facelib.sources.DirectorySource;
import org.vous.facelib.sources.DirectorySourceReader;
import org.vous.facelib.tests.util.FramePanel;
import org.vous.facelib.tests.util.TestApp;


public class DirectoryTest implements IFrameListener
{
	private FramePanel mPanel;

	public static void main(String[] args) throws Exception
	{
		new DirectoryTest().demo();
	}

	public void demo() throws Exception
	{
		String path = "c:\\images\\";
		TestApp app = new TestApp("DirectoryTest " + path);
		mPanel = new FramePanel();
		mPanel.setSize(Toolkit.getDefaultToolkit().getScreenSize());

		DirectorySource source = new DirectorySource(path, 10);
		source.getFrameListeners().addListener(this);

		DirectorySourceReader reader = source.connect();

		app.getContentPane().add(mPanel);
		final DirectorySourceReader readerRef = reader;
		app.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent event)
			{
				try
				{
					readerRef.disconnect();
				}

				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});

		app.setVisible(true);
	}

	IFilter filter = new GrayscaleFilter();

	@Override
	public void frameUpdated(Bitmap frame)
	{
		mPanel.updateFrame(filter.apply(frame));
	}
}
