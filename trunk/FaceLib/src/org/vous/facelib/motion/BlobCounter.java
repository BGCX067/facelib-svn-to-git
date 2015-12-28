package org.vous.facelib.motion;

import java.util.LinkedList;
import org.vous.facelib.bitmap.Bitmap;

public class BlobCounter
{
	private Bitmap mSource;
	LinkedList<Blob> mBlobs;
	
	public BlobCounter()
	{
		mBlobs = new LinkedList<Blob>();
	}
	
	public Bitmap getSource()
	{
		return mSource;
	}
	
	public void setBitmap(Bitmap source)
	{
		mSource = source;
	}

	public void process()
	{
		mBlobs.clear();
		
		/*
		 * .... find and add blobs
		 */
		
	}
	
	public Blob[] getObjects()
	{
		
		return mBlobs.toArray(new Blob[0]);
	}

}
