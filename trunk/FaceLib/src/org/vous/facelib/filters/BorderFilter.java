package org.vous.facelib.filters;

import java.awt.Color;
import java.awt.Graphics;

import org.vous.facelib.bitmap.Bitmap;

public class BorderFilter implements IFilter
{
	private Color mColor;
	private int mThickness;
	
	public BorderFilter(Color color, int thickness)
	{
		mColor = color;
		mThickness = thickness;
	}

	@Override
    public Bitmap apply(Bitmap source)
    {
		Bitmap dest = source.clone();
		Graphics g = dest.getGraphics();
		
		g.setColor(mColor);
		
		g.fillRect(0, 0, dest.getWidth(), mThickness);
		g.fillRect(0, 0, mThickness, dest.getHeight());
		g.fillRect(0, dest.getHeight() - mThickness, dest.getWidth(), mThickness);
		g.fillRect(dest.getWidth() - mThickness, 0, dest.getHeight(), dest.getHeight());
		
		g.dispose();
		
	    return dest;
    }

}
