package org.vous.facelib.tests.util;

import org.vous.facelib.listeners.ListenerCollection;

public class ExitListenerCollection extends ListenerCollection<IExitListener>
{
	public void fireExiting()
	{
		for (IExitListener listener : super.getCollection())
			listener.appExiting();
	}
}
