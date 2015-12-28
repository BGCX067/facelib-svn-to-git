package org.vous.facelib.bitmap;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bitmap implements Cloneable
{
	private BufferedImage mImage;
	private int mType;
	private Dimension mSize;

	public static Bitmap fromFile(File file) throws FileNotFoundException,
	        IOException
	{
		BufferedImage backing = ImageIO.read(file);
		if (backing == null || backing.getWidth() == 0
		        || backing.getHeight() == 0)
			throw new IOException("Invalid image");

		return new Bitmap(backing);
	}

	public Bitmap(int width, int height, int type)
	{
		initialize(new BufferedImage(width, height, type));
	}

	public Bitmap(BufferedImage source)
	{
		initialize(source);
	}
	
	private void initialize(BufferedImage backing)
	{
		mImage = backing;
		mType = backing.getType();
		mSize = new Dimension(backing.getWidth(), backing.getHeight());
	}

	public BufferedImage getBackingImage()
	{
		return mImage;
	}

	public int getType()
	{
		return mType;
	}

	public int getWidth()
	{
		return mSize.width;
	}

	public int getHeight()
	{
		return mSize.height;
	}

	public Graphics2D getGraphics()
	{
		return (Graphics2D) mImage.getGraphics();
	}

	public Bitmap createCompatible()
	{
		return new Bitmap(getWidth(), getHeight(), getType());
	}

	public Bitmap createCompatible(int width, int height)
	{
		return new Bitmap(width, height, getType());
	}
	
	public Bitmap[] split(int cols, int rows)
	{
		int width = getWidth() / cols;
		int height = getHeight() / rows;

		Bitmap[] parts = new Bitmap[cols * rows];
		int index = 0;

		for (int y = 0; y < rows; y++)
		{
			for (int x = 0; x < cols; x++)
			{
				parts[index] = getSubBitmap(x * width, y * height, width, height);
				index++;
			}
		}

		return parts;
	}

	public void fill(Color color)
	{
		fill(color, 0, 0, getWidth(), getHeight());
	}

	public void fill(Color color, int x, int y, int width, int height)
	{
		Graphics g = getGraphics();

		g.setColor(color);
		g.fillRect(x, y, width, height);

		g.dispose();
	}

	public void drawTo(Bitmap dest, int x, int y)
	{
		Graphics g = dest.getGraphics();
		g.drawImage(mImage, x, y, getWidth(), getHeight(), null);
		g.dispose();
	}

	public void drawTo(Bitmap dest)
	{
		drawTo(dest, 0, 0);
	}

	public Bitmap scale(int width, int height)
	{
		Bitmap scaled = new Bitmap(width, height, getType());
		Graphics2D g2 = scaled.getGraphics();

		g2.drawImage(mImage, 0, 0, width, height, null);
		g2.dispose();

		return scaled;
	}

	public Bitmap scale(float factor)
	{
		int width = (int) (getWidth() * factor);
		int height = (int) (getHeight() * factor);

		return scale(width, height);
	}

	public int[] getPixels()
	{
		return getPixels(0, 0, getWidth(), getHeight());
	}

	public int[] getPixels(int x, int y, int w, int h)
	{
		int[] pixels = new int[w * h];

		if (mType == BufferedImage.TYPE_INT_ARGB
		        || mType == BufferedImage.TYPE_INT_RGB)
			mImage.getData().getDataElements(x, y, w, h, pixels);

		else
			mImage.getRGB(x, y, w, h, pixels, 0, w);

		return pixels;
	}

	public int getPixel(int x, int y)
	{
		int pixel;

		if (mType == BufferedImage.TYPE_INT_ARGB
		        || mType == BufferedImage.TYPE_INT_RGB)
			pixel = mImage.getRaster().getDataBuffer().getElem(x, y);

		else
			pixel = mImage.getRGB(x, y);

		return pixel;
	}

	public void setPixels(int x, int y, int width, int height, int[] pixels)
	{
		if (mType == BufferedImage.TYPE_INT_ARGB
		        || mType == BufferedImage.TYPE_INT_RGB)
			mImage.getRaster().setDataElements(x, y, width, height, pixels);

		else
			mImage.setRGB(x, y, width, height, pixels, 0, width);
	}
	
	public void setPixels(int[] pixels)
	{
		setPixels(0, 0, getWidth(), getHeight(), pixels);
	}
	
	public void setPixel(int x, int y, int pixel)
	{
		if (mType == BufferedImage.TYPE_INT_ARGB
		        || mType == BufferedImage.TYPE_INT_RGB)
			mImage.getRaster().getDataBuffer().setElem(x, y, pixel);

		else
			mImage.setRGB(x, y, pixel);
	}

	public Bitmap getSubBitmap(int x, int y, int width, int height)
	{
		BufferedImage img = getBackingImage().getSubimage(x, y, width, height);
		return new Bitmap(img);
	}

	public Bitmap clone()
	{
		Bitmap clone = createCompatible();
		drawTo(clone);

		return clone;
	}

}
