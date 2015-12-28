package org.vous.facelib.sources;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.vous.facelib.bitmap.Bitmap;


public class DirectorySource extends AbstractFrameSource
{
	private File mBasedir;
	private Bitmap[] mFrames;

	public DirectorySource(String basedir, int fps)
	{
		super(fps);
		mBasedir = new File(basedir);
	}

	protected Bitmap[] getFrames()
	{
		return mFrames;
	}

	private Bitmap[] getImagesInDir()
	{
		final String[] types = ImageIO.getReaderFormatNames();
		Bitmap[] frames = null;
		File[] files = null;

		FilenameFilter filter = new FilenameFilter()
		{
			public boolean accept(File dir, String filename)
			{
				String ext = filename.substring(filename.lastIndexOf('.') + 1,
				        filename.length());
				if (ext != null)
				{
					for (String type : types)
					{
						for (int i = 0; i < types.length; i++)
						{
							if (type.equalsIgnoreCase(types[i]))
								return true;
						}
					}
				}

				return false;
			}
		};

		files = mBasedir.listFiles(filter);
		frames = new Bitmap[files.length];

		for (int i = 0; i < files.length; i++)
		{
			try
			{
				frames[i] = new Bitmap(ImageIO.read(files[i]));
			}

			catch (IOException ioe)
			{
				continue;
			}
		}

		return frames;
	}

	public DirectorySourceReader connect() throws Exception
	{
		if (!mBasedir.isDirectory())
			throw new Exception(mBasedir.getAbsolutePath()
			        + " is not a directory");

		mFrames = getImagesInDir();

		return new DirectorySourceReader(this);
	}

}
