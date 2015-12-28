package org.vous.facelib.filters;

import org.vous.facelib.bitmap.PixelUtils;

public class GainFilter extends PointFilter
{
private float mFactor;
	
	public GainFilter(float factor)
	{
		setFactor(factor);
	}
	
	public Float getFactor()
	{
		return mFactor;
	}
	
	public void setFactor(float factor)
	{
		if (factor > 1.f)
			mFactor = 1.f;
		
		else if (factor < -1.f)
			mFactor = -1.f;
		
		else mFactor = factor;
	}

	@Override
    protected int filter(int x, int y, int rgb)
    {  
	    return PixelUtils.gain(rgb, mFactor);
    }

}
