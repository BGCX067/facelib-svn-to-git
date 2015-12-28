package org.vous.facelib.sources.util;

import java.util.Vector;
import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.Format;
import javax.media.format.RGBFormat;
import javax.media.format.VideoFormat;
import javax.media.protocol.DataSource;

import org.vous.facelib.sources.CameraSource;

public class Camera
{
	/**
	 * 
	 * @return CaptureDeviceInfo[] array of supported devices on this system
	 */
	@SuppressWarnings("unchecked")
	public static CaptureDeviceInfo[] getDevices()
	{
		Vector<CaptureDeviceInfo> devices = CaptureDeviceManager
		        .getDeviceList(null);
		Vector<CaptureDeviceInfo> videoDevices = new Vector<CaptureDeviceInfo>();

		for (CaptureDeviceInfo devInfo : devices)
		{
			Format[] formats = devInfo.getFormats();
			for (Format format : formats)
			{
				if (format instanceof VideoFormat)
				{
					videoDevices.add(devInfo);
					break;
				}
			}
		}

		return videoDevices.toArray(new CaptureDeviceInfo[0]);
	}

	/**
	 * 
	 * @param devInfo
	 *            Device to probe
	 * @return RGBFormat[] array of formats supported by device
	 */
	public static RGBFormat[] getRGBFormats(CaptureDeviceInfo devInfo)
	{
		Format[] allFormats = devInfo.getFormats();
		Vector<RGBFormat> rgbFormats = new Vector<RGBFormat>();

		for (Format format : allFormats)
		{
			if (format instanceof RGBFormat)
			{
				rgbFormats.add((RGBFormat) format);
			}
		}

		return rgbFormats.toArray(new RGBFormat[0]);
	}

	/**
	 * 
	 * @param devInfo
	 *            Device to probe
	 * @return RGBFormat representing the highest resolution supported by device
	 */
	public static RGBFormat getHighestResolutionFormat(CaptureDeviceInfo devInfo)
	{
		RGBFormat[] formats = Camera.getRGBFormats(devInfo);
		RGBFormat chosen = formats[0];

		for (RGBFormat format : formats)
		{
			if (format.equals(formats[0]))
				continue;

			if (format.getSize().getWidth() * format.getSize().getHeight() > chosen
			        .getSize().getWidth()
			        * chosen.getSize().getHeight())
				chosen = format;
		}

		return chosen;
	}

	/**
	 * 
	 * @param devInfo
	 *            Device to probe
	 * @return RGBFormat representing the lowest resolution supported by this
	 *         device
	 */
	public static RGBFormat getLowestResolutionFormat(CaptureDeviceInfo devInfo)
	{
		RGBFormat[] formats = Camera.getRGBFormats(devInfo);
		RGBFormat chosen = formats[0];

		for (RGBFormat format : formats)
		{
			if (format.equals(formats[0]))
				continue;

			if (format.getSize().getWidth() * format.getSize().getHeight() < chosen
			        .getSize().getWidth()
			        * chosen.getSize().getHeight())
				chosen = format;
		}

		return chosen;
	}

	/**
	 * 
	 * @param devInfo
	 *            Device to probe
	 * @return true if camera represented by device is plugged in, otherwise
	 *         false
	 */
	public static boolean cameraPluggedIn(CaptureDeviceInfo devInfo)
	{
		DataSource source = null;

		try
		{
			source = CameraSource.getConnectedDataSource(devInfo);
		} catch (Exception e)
		{
			return false;
		}

		source.disconnect();
		return true;
	}

	/**
	 * 
	 * @return true if a supported camera is installed on this system, otherwise
	 *         false
	 */
	public static boolean cameraInstalled()
	{
		CaptureDeviceInfo[] devices = Camera.getDevices();
		return !(devices == null || devices.length == 0);
	}
}
