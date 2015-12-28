package org.vous.facelib.tests.editor;

import java.awt.Graphics;
import java.awt.Point;

import org.vous.facelib.bitmap.Bitmap;
import org.vous.facelib.bitmap.FilteredBitmapSplitter;
import org.vous.facelib.filters.FilterSequence;


public class FilterVisualizer extends FilteredBitmapSplitter
{
	private Point[] mPoints;

	public FilterVisualizer(FilterSequence sequence, Bitmap source, int cols,
	        int rows)
	{
		super(sequence, source, cols, rows);
		mPoints = new Point[cols * rows];

		int x, y = 0;
		for (int i = 0; i < mPoints.length; i++)
		{
			x = 0; // ??!?!?
			y = 0; // ?!???!
			mPoints[i] = new Point(x, y);
		}
	}

	public Bitmap next()
	{
		int index = super.getIndex();
		Bitmap next = super.next();
		Bitmap source = super.getSource();
		Graphics g = source.getGraphics();
		Point point = mPoints[index];

		g.drawImage(next.getBackingImage(), point.x, point.y, null);
		g.dispose();

		return source;
	}
}
