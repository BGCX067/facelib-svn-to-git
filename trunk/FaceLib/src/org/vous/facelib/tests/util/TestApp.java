package org.vous.facelib.tests.util;

import javax.swing.JFrame;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;

public class TestApp extends JFrame
{
	public static LookAndFeel LnF = new SubstanceBusinessBlackSteelLookAndFeel();

	public final static long serialVersionUID = 0;
	private LookAndFeel mLnf;

	public TestApp(String title)
	{
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		mLnf = null;
	}

	public void applyLnF(LookAndFeel lnf) throws Exception
	{
		mLnf = lnf;

		JFrame.setDefaultLookAndFeelDecorated(true);
		UIManager.setLookAndFeel(lnf);

		SwingUtilities.updateComponentTreeUI(this);
	}

	public LookAndFeel getLnF()
	{
		return mLnf;
	}
}
