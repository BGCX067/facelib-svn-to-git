package org.vous.facelib.filters;

import java.awt.Graphics;

import org.vous.facelib.bitmap.Bitmap;


public class MirrorFilter implements IFilter
{
	public Bitmap apply(Bitmap source)
	{
		int width = source.getWidth();
		int height = source.getHeight();
		Bitmap filtered = source.createCompatible();

		Graphics g = filtered.getGraphics();

		g.drawImage(source.getBackingImage(), 0, 0, width, height, width, 0, 0,
		        height, null);
		g.dispose();

		return filtered;
	}

	public String toString()
	{
		return "Mirror filter";
	}
}
