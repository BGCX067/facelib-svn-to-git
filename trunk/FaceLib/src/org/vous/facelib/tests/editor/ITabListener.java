package org.vous.facelib.tests.editor;

public interface ITabListener
{
	public void tabCountChanged(int tabCount);

	public void tabSelected(Tab tab);

	public void tabLocked(Tab tab);

	public void tabUnlocked(Tab tab);

	public void tabUndoLevelChanged(Tab tab, int level);
}
