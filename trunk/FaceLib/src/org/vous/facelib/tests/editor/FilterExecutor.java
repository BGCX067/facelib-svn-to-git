package org.vous.facelib.tests.editor;

import javax.swing.SwingWorker;

import org.vous.facelib.bitmap.Bitmap;
import org.vous.facelib.filters.FilterSequence;
import org.vous.facelib.filters.IFilter;


public class FilterExecutor
{
	private SwingWorker<Bitmap, Void> mWorker;
	private Tab mTab;

	public FilterExecutor(Tab tab)
	{
		mWorker = null;
		mTab = tab;
	}

	public void execute(IFilter filter)
	{
		final Bitmap source = mTab.getCurrentFrame().clone();
		final FilterSequence seq = new FilterSequence();
		seq.addFilter(filter);

		mTab.lock();

		mWorker = new SwingWorker<Bitmap, Void>()
		{
			public Bitmap doInBackground()
			{
				return seq.apply(source);
			}

			public void done()
			{
				try
				{
					Bitmap result = get();
					mTab.getUndo().addLevel(source);
					mTab.updateFrame(result);
				}

				catch (Exception e)
				{
					// mStatus.setText("Error: " + e.getMessage());
				}

				finally
				{
					mTab.unlock();
				}
			}
		};

		mWorker.execute();
	}

	public void cancel()
	{
		if (mWorker != null)
			mWorker.cancel(true);
	}

}
