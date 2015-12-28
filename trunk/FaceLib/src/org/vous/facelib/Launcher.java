package org.vous.facelib;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.imageio.ImageIO;
import org.vous.facelib.bitmap.Bitmap;
import org.vous.facelib.filters.GainFilter;
import org.vous.facelib.filters.FilterSequence;
import org.vous.facelib.filters.GrayscaleFilter;
import org.vous.facelib.filters.JitterFilter;
import org.vous.facelib.filters.MaskOutFilter;
import org.vous.facelib.filters.PixellationFilter;
import org.vous.facelib.tests.editor.FilterVisualizer;
import org.vous.facelib.tests.util.FrameViewingApp;


public class Launcher extends KeyAdapter
{
	FrameViewingApp mApp;
	private Bitmap mSource;

	public static void main(String[] args) throws Exception
	{
		new Launcher().demo();
	}
	
	public void demo() throws Exception
	{
		Bitmap source = Bitmap.fromFile(new File("c:\\mig.jpg"));
		source = new JitterFilter(5).apply(source);
		
		new FrameViewingApp("Jitter").updateFrame(source);
	}
	
	public void test() throws Exception
	{
		mApp = new FrameViewingApp("split");
		mApp.setSize(new Dimension(800, 600));
		mApp.addKeyListener(this);
		mSource = Bitmap.fromFile(new File("c:\\mig.jpg"));
	}

	protected void save(Bitmap frame, int index)
	{
		File file = new File("c:\\frame" + index + ".png");

		try
		{
			ImageIO.write(frame.getBackingImage(), "png", file);
		} catch (Exception e)
		{
			System.err.println(e);
		}
	}

	public void keyPressed(KeyEvent event)
	{
		FilterSequence seq = new FilterSequence();
		seq.addFilter(new GrayscaleFilter());
		FilterVisualizer fv = new FilterVisualizer(seq, mSource, 5, 5);

		while (fv.hasNext())
		{
			mApp.updateFrame(fv.next());
		}
	}
}
