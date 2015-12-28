package org.vous.facelib.filters;

import java.awt.Graphics;
import java.awt.Rectangle;

import org.vous.facelib.bitmap.Bitmap;
import org.vous.facelib.motion.Blob;
import org.vous.facelib.motion.BlobCounter;

public class BlobFilter implements IFilter
{
	private BlobCounter mCounter;
	
	public BlobFilter()
	{
		mCounter = new BlobCounter();
	}

	@Override
    public Bitmap apply(Bitmap source)
    {
	   Bitmap dest = source.clone();
	   
	   mCounter.setBitmap(source);
	   mCounter.process();
	   
	   Blob[] blobs = mCounter.getObjects();
	   
	   Graphics g = dest.getGraphics();
	   for (Blob blob : blobs)
	   {
		   Rectangle loc = blob.getLocation();
		   g.drawRect(loc.x, loc.y, loc.width, loc.height);
		   
	   }
	   g.dispose();
	   
	   return dest;
    }

}
