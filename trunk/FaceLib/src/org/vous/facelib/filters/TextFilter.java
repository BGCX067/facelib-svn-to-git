package org.vous.facelib.filters;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import org.vous.facelib.bitmap.Bitmap;

public class TextFilter implements IFilter
{
	private String mText;
	private Font mFont;
	private int mStartx, mStarty;
	private Color mColor;
	
	public TextFilter(Font font, Color color, String text, int startx, int starty)
	{
		mFont = font;
		mColor = color;
		mText = text;
		mStartx = startx;
		mStarty = starty;
	}
	
	public void setFont(Font font)
	{
		mFont = font;
	}
	
	public Font getFont()
	{
		return mFont;
	}
	
	public void setColor(Color color)
	{
		mColor = color;
	}
	
	public Color getColor()
	{
		return mColor;
	}
	
	public void setText(String text)
	{
		mText = text;
	}
	
	public String getText()
	{
		return mText;
	}
	
	public void setStartX(int x)
	{
		mStartx = x;
	}
	
	public int getStartX()
	{
		return mStartx;
	}
	
	public void setStartY(int y)
	{
		mStarty = y;
	}
	
	public int getStartY()
	{
		return mStarty;
	}
	
	@Override
    public Bitmap apply(Bitmap source)
    {
	   Bitmap dest = source.clone();
	   Graphics g = dest.getGraphics();
	   
	   g.setFont(mFont);
	   g.setColor(mColor);
	   g.drawString(mText, mStartx, mStarty);
	   g.dispose();
	   
	   return dest;
    }
}
