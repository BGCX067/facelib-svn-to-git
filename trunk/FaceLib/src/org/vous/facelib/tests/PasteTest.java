package org.vous.facelib.tests;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.vous.facelib.bitmap.Bitmap;


public class PasteTest
{
	public static void main(String[] args) throws Exception
	{
		new PasteTest().demo();
	}

	public void demo() throws Exception
	{
		Bitmap empty = new Bitmap(800, 800, BufferedImage.TYPE_INT_ARGB);
		Bitmap cropped = Bitmap.fromFile(new File("c:\\images\\image1.jpg"));

		cropped.drawTo(empty);

		ImageIO
		        .write(empty.getBackingImage(), "png",
		                new File("c:\\paste.png"));
	}
}
