package org.vous.facelib.filters;

import org.vous.facelib.bitmap.Bitmap;

public class FlipFilter implements IFilter
{

	@Override
    public Bitmap apply(Bitmap source)
    {
	    Bitmap dest = source.createCompatible();
	    int w = dest.getWidth(), h = dest.getHeight();
	    int[] sourcePixels = source.getPixels();	   
	    int[] destPixels = new int[w * h];
	    
	    int i = 0, j = sourcePixels.length - 1;
	    while (i < sourcePixels.length)
	    {
	    	destPixels[i] = sourcePixels[j];
	    	j --;
	    	i ++;
	    }
	    
	    dest.setPixels(destPixels);
	    return dest;
    }

}