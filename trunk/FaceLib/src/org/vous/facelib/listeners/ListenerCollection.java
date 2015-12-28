package org.vous.facelib.listeners;

import java.util.LinkedList;

public class ListenerCollection<T>
{
	private LinkedList<T> mListeners;

	public ListenerCollection()
	{
		mListeners = new LinkedList<T>();
	}

	protected LinkedList<T> getCollection()
	{
		return mListeners;
	}

	public void addListener(T listener)
	{
		mListeners.add(listener);
	}

	public boolean removeListener(T listener)
	{
		return mListeners.remove(listener);
	}
}
