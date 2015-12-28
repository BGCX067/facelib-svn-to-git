package org.vous.facelib.filters;

import java.awt.Color;
import java.awt.Graphics;

import org.vous.facelib.bitmap.Bitmap;

public class FillFilter implements IFilter
{
	private Color mFill;
	
	public FillFilter(Color fill)
	{
		mFill = fill;
	}
	
	public Color getFillColor()
	{
		return mFill;
	}
	
	public void setFillColor(Color fill)
	{
		mFill = fill;
	}

	@Override
    public Bitmap apply(Bitmap source)
    {
	   Bitmap dest = source.createCompatible();
	   Graphics g = dest.getGraphics();
	   
	   g.setColor(mFill);
	   g.fillRect(0, 0, dest.getWidth(), dest.getHeight());
	   g.dispose();
	   
	   return dest;
    }

}
