package org.vous.facelib.filters;

import java.awt.Color;
import org.vous.facelib.bitmap.PixelUtils;

public class TriToneFilter extends PointFilter
{
	private Color mLow, mHigh, mMid;
	private int mThresholdLow = 50;
	private int mThresholdMid = 175;
	
	public TriToneFilter(Color low, Color high, Color mid)
	{
		mLow = low;
		mHigh = high;
		mMid = mid;
	}

	@Override
    protected int filter(int x, int y, int rgb)
    {
		int b = PixelUtils.brightness(rgb);
		if (b <= mThresholdLow)
			return mLow.getRGB();
		
		else if (b <= mThresholdMid)
			return mMid.getRGB();
		
		return mHigh.getRGB();
    }
}
