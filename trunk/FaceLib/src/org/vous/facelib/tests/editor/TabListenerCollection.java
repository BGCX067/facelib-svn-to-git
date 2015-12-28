package org.vous.facelib.tests.editor;

import org.vous.facelib.listeners.ListenerCollection;

public class TabListenerCollection extends ListenerCollection<ITabListener>
{
	public void fireTabCountChanged(int newCount)
	{
		for (ITabListener listener : super.getCollection())
			listener.tabCountChanged(newCount);
	}

	public void fireTabSelected(Tab tab)
	{
		for (ITabListener listener : super.getCollection())
			listener.tabSelected(tab);
	}

	public void fireTabLocked(Tab tab)
	{
		for (ITabListener listener : super.getCollection())
			listener.tabLocked(tab);
	}

	public void fireTabUnlocked(Tab tab)
	{
		for (ITabListener listener : super.getCollection())
			listener.tabUnlocked(tab);
	}

	public void fireTabUndoLevelChanged(Tab tab, int level)
	{
		for (ITabListener listener : super.getCollection())
			listener.tabUndoLevelChanged(tab, level);
	}
}
