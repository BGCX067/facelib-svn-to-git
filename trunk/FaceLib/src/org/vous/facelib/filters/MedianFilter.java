package org.vous.facelib.filters;

import org.vous.facelib.bitmap.Bitmap;

public class MedianFilter implements IFilter
{
	public Bitmap apply(Bitmap source)
	{
		int width = source.getWidth();
		int height = source.getHeight();
		int[] pixels = null;
		Bitmap filtered = source.createCompatible();

		pixels = source.getPixels(0, 0, width, height);
		pixels = filterPixels(width, height, pixels);
		filtered.setPixels(pixels);

		return filtered;
	}

	public String toString()
	{
		return "Median filter";
	}

	private int RGBMedian(int[] r, int[] g, int[] b)
	{
		int sum;
		int index = 0;
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < 9; i++)
		{
			sum = 0;
			for (int j = 0; j < 9; j++)
			{
				sum += Math.abs(r[i] - r[j]);
				sum += Math.abs(g[i] - g[j]);
				sum += Math.abs(b[i] - b[j]);
			}

			if (sum < min)
			{
				min = sum;
				index = i;
			}

		}

		return index;
	}

	private int[] filterPixels(int width, int height, int[] inPixels)
	{
		int index = 0;
		int[] argb = new int[9];
		int[] r = new int[9];
		int[] g = new int[9];
		int[] b = new int[9];
		int[] outPixels = new int[width * height];

		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				int k = 0;
				for (int dy = -1; dy <= 1; dy++)
				{
					int iy = y + dy;
					if (0 <= iy && iy < height)
					{
						int ioffset = iy * width;
						for (int dx = -1; dx <= 1; dx++)
						{
							int ix = x + dx;
							if (0 <= ix && ix < width)
							{
								int rgb = inPixels[ioffset + ix];
								argb[k] = rgb;
								r[k] = (rgb >> 16) & 0xff;
								g[k] = (rgb >> 8) & 0xff;
								b[k] = rgb & 0xff;
								k++;
							}
						}
					}
				}

				while (k < 9)
				{
					argb[k] = 0xff000000;
					r[k] = g[k] = b[k] = 0;
					k++;
				}

				outPixels[index++] = argb[RGBMedian(r, g, b)];
			}
		}
		return outPixels;
	}
}