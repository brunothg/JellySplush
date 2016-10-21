package com.github.brunothg.jellysplush;

import java.awt.Color;
import java.io.FileInputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Settings
{

	private static final Logger LOG = LoggerFactory.getLogger(Settings.class);

	public static final String KEY_MAX_LIFES = "max_lifes";
	public static final String KEY_MAX_POINTS = "max_points";
	public static final String KEY_FREE_MOVEMENT = "enable_free_movement";
	public static final String KEY_SPEED = "speed";
	public static final String KEY_USED_POWERUPS = "powerups_used";
	public static final String KEY_BACKGROUND_COLOR = "background_color";
	public static final String KEY_USER_COLORS = "user_colors";
	public static final String KEY_PLAYGROUND_WIDTH = "playground_width";
	public static final String KEY_PLAYGROUND_HEIGHT = "playground_height";
	public static final String KEY_STRATEGY = "powerup_strategy";
	public static final String KEY_POWERUP_TIME = "powerup_time";
	public static final String KEY_POWERUP_LIFETIME = "powerup_lifetime";

	public static final Properties props = new Properties(System.getProperties());

	static
	{

		try
		{
			props.load(new FileInputStream("./settings.properties"));
		}
		catch (Exception e)
		{
			LOG.warn("Could not load properties", e);
		}
	}

	public static boolean getBoolean(String key, boolean def)
	{

		try
		{
			String property = props.getProperty(key).trim();

			if (property == null)
			{

				return def;
			}
			else
			{

				try
				{
					return Boolean.valueOf(property);
				}
				catch (Exception e)
				{
					LOG.warn("Could not load boolean property {}", key, e);
				}
			}
		}
		catch (Exception e)
		{
			LOG.warn("Could not load boolean property {}", key, e);
			return def;
		}

		return def;
	}

	public static Color getColor(String key, Color def)
	{

		try
		{
			String property = props.getProperty(key).trim();

			if (property == null)
			{

				return def;
			}
			else
			{

				try
				{
					return Color.decode(property);
				}
				catch (Exception e)
				{
					LOG.warn("Could not load color property {}", key, e);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return def;
		}
		return def;
	}

	public static Color[] getColorArray(String key, Color[] def)
	{

		try
		{
			String property = props.getProperty(key);

			if (property == null)
			{

				return def;
			}
			else
			{

				try
				{
					String[] colors = property.split(",");
					Color[] cols = new Color[colors.length];

					for (int i = 0; i < cols.length; i++)
					{

						cols[i] = Color.decode(colors[i].trim());
					}

					return cols;
				}
				catch (Exception e)
				{
					LOG.warn("Could not load color array property {}", key, e);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return def;
		}

		return def;
	}

	public static int getInt(String key, int def)
	{

		try
		{
			String property = props.getProperty(key).trim();

			if (property == null)
			{

				return def;
			}
			else
			{

				try
				{
					return Integer.valueOf(property);
				}
				catch (Exception e)
				{
					LOG.warn("Could not load integer property {}", key, e);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return def;
		}

		return def;
	}

	public static int[] getIntArray(String key, int[] def)
	{

		try
		{
			String property = props.getProperty(key);

			if (property == null)
			{

				return def;
			}
			else
			{

				try
				{

					String[] ints = property.split(",");
					int[] ret = new int[ints.length];

					int index = 0;
					for (String s : ints)
					{

						ret[index] = Integer.valueOf(s.trim());
						index++;
					}

					return ret;
				}
				catch (Exception e)
				{
					LOG.warn("Could not load integer array property {}", key, e);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return def;
		}

		return def;
	}

	public static double getDouble(String key, double def)
	{

		try
		{
			String property = props.getProperty(key).trim();

			if (property == null)
			{

				return def;
			}
			else
			{

				try
				{
					return Double.valueOf(property);
				}
				catch (Exception e)
				{
					LOG.warn("Could not load double property {}", key, e);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return def;
		}

		return def;
	}

	public static Class<?> getClass(String key, Class<?> def)
	{

		Class<?> ret = def;
		try
		{

			try
			{
				Class<?> load = Class.forName(props.getProperty(key));

				if (load != null)
				{

					ret = load;
				}
			}
			catch (ClassNotFoundException e)
			{
			}
		}
		catch (Exception e)
		{
			LOG.warn("Could not load class property {}", key, e);
		}

		return ret;
	}
}
