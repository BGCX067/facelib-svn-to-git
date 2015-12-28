package org.vous.facelib.filters;

import org.vous.facelib.bitmap.Bitmap;

public class MoveTowardsFilter implements IFilter
{
	private Bitmap mOverlay;
	private int mSteps;
	
	public MoveTowardsFilter(Bitmap overlay, int steps)
	{
		mOverlay = overlay;
		mSteps = steps;
	}
	
	@Override
	public Bitmap apply(Bitmap source)
	{		
		Bitmap dest = source.createCompatible();
		dest.setPixels(source.getPixels());
		
		int width = mOverlay.getWidth();
		int height = 1;
		for (int i = 0; i < mSteps; i ++)
		{
			int[] stepPixels = mOverlay.getPixels(0, i, width, height);
			dest.setPixels(0, i, width, height, stepPixels);
		}
		
		return dest;
	}

}
