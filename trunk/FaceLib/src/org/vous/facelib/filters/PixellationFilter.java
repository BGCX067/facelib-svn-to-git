package org.vous.facelib.filters;

import org.vous.facelib.bitmap.Bitmap;
import org.vous.facelib.bitmap.PixelUtils;

public class PixellationFilter implements IFilter
{
	private int mCellheight, mCellwidth;
	
	public PixellationFilter(int width, int height)
	{
		mCellwidth = width;
		mCellheight = height;
	}
	
	@Override
    public Bitmap apply(Bitmap source)
    {	
		Bitmap dest = source.createCompatible();
		int w = dest.getWidth();
		int h = dest.getHeight();
		
		for (int x = 0; x < w; x += mCellwidth)
			for (int y = 0; y < h; y += mCellheight)
			{
				int[] px = source.getPixels(x, y, mCellwidth, mCellheight);
				int avg = avgRGB(px);
				for (int i = 0; i < px.length; i ++)
					px[i] = avg;
				
				dest.setPixels(x, y, mCellwidth, mCellheight, px);
			}
		
		return dest;
    }
	
	private int avgRGB(int[] pixels)
	{
		int r = 0, g = 0, b = 0;
		
		for (int i = 0; i < pixels.length; i ++)
		{
			r += PixelUtils.red(pixels[i]);
			g += PixelUtils.green(pixels[i]);
			b += PixelUtils.blue(pixels[i]);
		}
		
		return PixelUtils.toRGB(r / pixels.length, g / pixels.length, b / pixels.length);
	}

}
