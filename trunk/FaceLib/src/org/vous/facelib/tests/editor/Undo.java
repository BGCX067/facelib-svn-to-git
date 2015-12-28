package org.vous.facelib.tests.editor;

import java.util.Stack;

import org.vous.facelib.bitmap.Bitmap;


public class Undo
{
	private Stack<Bitmap> mStack;
	private Tab mTab;

	public Undo(Tab tab)
	{
		mTab = tab;
		mStack = new Stack<Bitmap>();
	}

	public Bitmap getPrevLevel()
	{
		Bitmap frame = null;

		if (!mStack.isEmpty())
		{
			frame = mStack.pop();
			mTab.getOwner().fireTabUndoLevelChanged(mTab, mStack.size());
		}

		return frame;
	}

	public void addLevel(Bitmap frame)
	{
		mStack.push(frame);
		mTab.getOwner().fireTabUndoLevelChanged(mTab, mStack.size());
	}

	public int getLevelCount()
	{
		return mStack.size();
	}
}
