package org.vous.facelib.filters;

import java.awt.Rectangle;

import org.vous.facelib.bitmap.Bitmap;

public class ZoomFilter implements IFilter
{	
	private float mFactor;
	private Rectangle mArea;
	
	public ZoomFilter(float factor, Rectangle area)
	{
		setFactor(factor);
		setArea(area);
	}

	public float getFactor()
	{
		return mFactor;
	}
	
	public void setFactor(float factor)
	{
		mFactor = factor;
	}
	
	public Rectangle getArea()
	{
		return mArea;
	}
	
	public void setArea(Rectangle area)
	{
		mArea = area;
	}
	
	@Override
    public Bitmap apply(Bitmap source)
    {
		Bitmap dest = source.getSubBitmap(mArea.x, mArea.y, mArea.width, mArea.height);
		return dest.scale(mFactor);
    }

}
