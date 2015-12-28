package org.vous.facelib.bitmap;

public class PixelUtils
{	
	public static int alpha(int rgb)
	{
		return (rgb >> 24) & 0xff;
	}
	
	public static int red(int rgb)
	{
		return (rgb >> 16) & 0xff;
	}
	
	public static int green(int rgb)
	{
		return (rgb >> 8) & 0xff;
	}
	
	public static int blue(int rgb)
	{
		return rgb & 0xff;
	}
	
	public static int brightness(int rgb)
	{
		return (red(rgb) + green(rgb) + blue(rgb)) / 3;
	}
	
	public static int interpolate(int v1, int v2, float factor)
	{
		return clamp((int) (v1 + factor * (v2 - v1)));
	}
	
	public static int invert(int rgb)
	{
		int r = 255 - red(rgb);
		int g = 255 - green(rgb);
		int b = 255 - blue(rgb);
		
		return toARGB(255, r, g, b);
	}
	
	public static int toARGB(int a, int r, int g, int b)
	{
		return 
			((a & 0xFF) << 24) |
			((r & 0xFF) << 16) |
			((g & 0xFF) << 8)  |
			((b & 0xFF) << 0);
	}
	
	public static int toRGB(int r, int g, int b)
	{
		return
		((r & 0xFF) << 16) |
		((g & 0xFF) << 8) |
		((b & 0xFF) << 0);
		
	}
	
	public static boolean equals(int rgb1, int rgb2)
	{
		int r1 = red(rgb1);
		int g1 = green(rgb1);
		int b1 = blue(rgb1);
		int r2 = red(rgb2);
		int g2 = green(rgb2);
		int b2 = blue(rgb2);
		
		return (r1 == r2 && g1 == g2 && b1 == b2);
	}
	
	public static int clamp(int ch)
	{
		if (ch < 0)
			return 0;
		
		if (ch > 255)
			return 255;
		
		return ch;		
	}
	
	public static int gain(int rgb, float factor)
	{
		int r = PixelUtils.red(rgb);
		r = PixelUtils.clamp((int) (r + ((int) r * factor)));
		int g = PixelUtils.green(rgb);
		g = PixelUtils.clamp((int) (g + ((int) g * factor)));
		int b = PixelUtils.blue(rgb);
		b = PixelUtils.clamp((int) (b + ((int) b * factor)));
		
		return toARGB(alpha(rgb), r, g, b);
	}
}
