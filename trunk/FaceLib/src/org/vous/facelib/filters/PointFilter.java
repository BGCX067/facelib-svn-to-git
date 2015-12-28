package org.vous.facelib.filters;

import org.vous.facelib.bitmap.Bitmap;

public abstract class PointFilter implements IFilter
{
	protected abstract int filter(int x, int y, int rgb);
	
	@Override
    public Bitmap apply(Bitmap source)
    {
		Bitmap dest = source.clone();
		int w = source.getWidth();
		int h = source.getHeight();
		
		for (int x = 0; x < w; x ++)
			for (int y = 0; y < h; y ++)
			{
				int rgb = dest.getPixel(x, y);
				dest.setPixel(x, y, filter(x, y, rgb));
			}
		
		return dest;
    }

	
}
