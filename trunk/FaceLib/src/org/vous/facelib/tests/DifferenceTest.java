package org.vous.facelib.tests;

import java.io.File;

import javax.imageio.ImageIO;

import org.vous.facelib.bitmap.Bitmap;
import org.vous.facelib.filters.DifferenceFilter;


public class DifferenceTest
{
	public static void main(String[] args)
	{
		new DifferenceTest().demo();
	}

	public void demo()
	{
		File orig = new File("c:\\images\\image8.jpg"), comp = new File(
		        "c:\\images\\image10.jpg");

		try
		{
			Bitmap fOrig = Bitmap.fromFile(orig);
			Bitmap cOrig = Bitmap.fromFile(comp);
			DifferenceFilter filter = new DifferenceFilter(fOrig);

			Bitmap diff = filter.apply(cOrig);

			ImageIO.write(diff.getBackingImage(), "png", new File(
			        "c:\\diff.png"));
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
}
