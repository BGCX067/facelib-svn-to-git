package org.vous.facelib.filters;

import org.vous.facelib.bitmap.Bitmap;
import org.vous.facelib.bitmap.PixelUtils;

public class InvertAlphaFilter implements IFilter
{

	@Override
    public Bitmap apply(Bitmap source)
    {
		Bitmap dest = source.clone();
		
		if (!dest.getBackingImage().getColorModel().hasAlpha())
			return dest;
		
		int[] pixels = dest.getPixels();
		
		for (int i = 0; i < pixels.length; i ++)
		{
			int a = 255 - PixelUtils.alpha(pixels[i]);
			int r = PixelUtils.red(pixels[i]);
			int g = PixelUtils.green(pixels[i]);
			int b = PixelUtils.blue(pixels[i]);
			pixels[i] = PixelUtils.toARGB(a, r, g, b);
		}
		
		dest.setPixels(pixels);
		return dest;
    }

}
