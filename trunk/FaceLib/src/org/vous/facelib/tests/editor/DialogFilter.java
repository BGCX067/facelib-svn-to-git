package org.vous.facelib.tests.editor;

import java.io.File;
import javax.swing.filechooser.FileFilter;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class DialogFilter extends FileFilter
{
	private String[] mTypes;

	public DialogFilter()
	{
		LinkedList<String> sorted = new LinkedList<String>();

		for (String type : ImageIO.getReaderFormatNames())
		{
			type = type.toLowerCase();
			if (!sorted.contains(type))
				sorted.add(type);
		}

		mTypes = sorted.toArray(new String[0]);
	}

	public boolean accept(File file)
	{
		if (file.isDirectory())
			return true;

		int extIdx = file.getName().lastIndexOf('.');
		if (extIdx > -1 && file.getName().length() > extIdx)
		{
			String ext = file.getName().substring(extIdx + 1);

			for (String format : mTypes)
			{
				if (format.equalsIgnoreCase(ext))
					return true;
			}
		}

		return false;

	}

	@Override
	public String getDescription()
	{
		String ret = "";

		for (String type : mTypes)
			ret += " *." + type;

		return ret;
	}

}
