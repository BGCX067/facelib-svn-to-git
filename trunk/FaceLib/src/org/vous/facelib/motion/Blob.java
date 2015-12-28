package org.vous.facelib.motion;

import java.awt.Rectangle;
import org.vous.facelib.bitmap.Bitmap;

public class Blob
{
	private Rectangle mLocation;
	private Bitmap mBitmap;
	
	public Blob(int x, int y, Bitmap bitmap)
	{
		mLocation = new Rectangle(x, y, bitmap.getWidth(), bitmap.getHeight());
		mBitmap = bitmap;
	}
	
	public Bitmap getImage()
	{
		return mBitmap;
	}
	
	public Rectangle getLocation()
	{
		return mLocation;
	}
	
	
}
