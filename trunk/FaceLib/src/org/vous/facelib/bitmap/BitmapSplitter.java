package org.vous.facelib.bitmap;


public class BitmapSplitter
{
	private Bitmap mSource;
	private Bitmap[] mSplits;
	private int mIndex;
	private int mCols, mRows;

	public BitmapSplitter(Bitmap source, int cols, int rows)
	{
		mSource = source;
		mSplits = source.split(cols, rows);
		mIndex = 0;
		mCols = cols;
		mRows = rows;
	}

	protected int getCols()
	{
		return mCols;
	}

	protected int getRows()
	{
		return mRows;
	}

	protected Bitmap getSource()
	{
		return mSource;
	}

	protected int getIndex()
	{
		return mIndex;
	}

	public boolean hasNext()
	{
		if (mIndex == mSplits.length)
		{
			mIndex = 0;
			return false;
		}

		return true;
	}

	public Bitmap next()
	{
		Bitmap next = mSplits[mIndex];
		mIndex++;

		return next;
	}

}
