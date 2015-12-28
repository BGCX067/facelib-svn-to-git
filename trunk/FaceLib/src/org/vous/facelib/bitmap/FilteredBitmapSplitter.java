package org.vous.facelib.bitmap;

import org.vous.facelib.filters.FilterSequence;

public class FilteredBitmapSplitter extends BitmapSplitter
{
	private FilterSequence mSequence;

	public FilteredBitmapSplitter(FilterSequence sequence, Bitmap source,
	        int cols, int rows)
	{
		super(source, cols, rows);
		mSequence = sequence;
	}

	public Bitmap next()
	{
		Bitmap next = super.next();
		return mSequence.apply(next);
	}

}
