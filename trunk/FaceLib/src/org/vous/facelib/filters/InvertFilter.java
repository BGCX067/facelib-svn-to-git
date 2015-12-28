package org.vous.facelib.filters;

import org.vous.facelib.bitmap.PixelUtils;

public class InvertFilter extends PointFilter
{
	@Override
    protected int filter(int x, int y, int rgb)
    {
	    return PixelUtils.invert(rgb);
    }

}
