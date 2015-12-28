package org.vous.facelib.filters;

import org.vous.facelib.bitmap.Bitmap;

public interface IFilter
{
	public Bitmap apply(final Bitmap source);

}
