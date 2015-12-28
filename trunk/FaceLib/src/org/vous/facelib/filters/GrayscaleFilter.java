package org.vous.facelib.filters;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.vous.facelib.bitmap.Bitmap;


public class GrayscaleFilter implements IFilter
{
	public Bitmap apply(Bitmap source)
	{
		Bitmap gray = new Bitmap(source.getWidth(), source.getHeight(),
		        BufferedImage.TYPE_BYTE_GRAY);

		Graphics g = gray.getGraphics();
		g.drawImage(source.getBackingImage(), 0, 0, null);
		g.dispose();

		return gray;
	}

	public String toString()
	{
		return "Gray filter";
	}
}
