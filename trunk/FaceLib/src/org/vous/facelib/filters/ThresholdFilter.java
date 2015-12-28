package org.vous.facelib.filters;

import java.awt.Color;
import org.vous.facelib.bitmap.Bitmap;
import org.vous.facelib.bitmap.PixelUtils;

public class ThresholdFilter implements IFilter
{
	private int mFillWhite = Color.WHITE.getRGB();
	private int mFillBlack = Color.BLACK.getRGB();
	private int mThreshold;

	public ThresholdFilter(int threshold)
	{
		mThreshold = threshold;
	}

	public void setThreshold(int threshold)
	{
		mThreshold = threshold;
	}
	
	public int getThreshold()
	{
		return mThreshold;
	}

	@Override
	public Bitmap apply(Bitmap source)
	{
		Bitmap dest = source.createCompatible();
		int[] pixels = source.getPixels();
		
		for (int i = 0; i < pixels.length; i ++)
		{	
			int avg = PixelUtils.brightness(pixels[i]);
			if (avg < mThreshold)
				pixels[i] = mFillBlack;
			
			else pixels[i] = mFillWhite;
		}
		
		dest.setPixels(pixels);
		
		return dest;
	}

}
