package de.bno.jellysplush;

import java.io.FileInputStream;
import java.util.Properties;

public class Settings {

	public static final String KEY_MAX_LIFES = "max_lifes";
	public static final String KEY_MAX_POINTS = "max_points";
	public static final String KEY_FREE_MOVEMENT = "enable_free_movement";
	public static final String KEY_SPEED = "speed";
	public static final String KEY_USED_POWERUPS = "powerups_used";

	public static final Properties props = new Properties(
			System.getProperties());

	static {

		try {
			props.load(new FileInputStream("./settings.properties"));
		} catch (Exception e) {
			System.err.println(" -> Properties load ex: " + e.getMessage());
		}
	}

	public static boolean getBoolean(String key, boolean def) {

		String property = props.getProperty(key).trim();

		if (property == null) {

			return def;
		} else {

			try {
				return Boolean.valueOf(property);
			} catch (Exception e) {
				System.err.println(key + " -> Properties read ex: "
						+ e.getMessage());
			}
		}

		return def;
	}

	public static int getInt(String key, int def) {

		String property = props.getProperty(key).trim();

		if (property == null) {

			return def;
		} else {

			try {
				return Integer.valueOf(property);
			} catch (Exception e) {
				System.err.println(key + " -> Properties read ex: "
						+ e.getMessage());
			}
		}

		return def;
	}

	public static int[] getIntArray(String key, int[] def) {

		String property = props.getProperty(key);

		if (property == null) {

			return def;
		} else {

			try {

				String[] ints = property.split(",");
				int[] ret = new int[ints.length];

				int index = 0;
				for (String s : ints) {

					ret[index] = Integer.valueOf(s.trim());
					index++;
				}

				return ret;
			} catch (Exception e) {
				System.err.println(key + " -> Properties read ex: "
						+ e.getMessage());
			}
		}

		return def;
	}

	public static double getDouble(String key, double def) {

		String property = props.getProperty(key).trim();

		if (property == null) {

			return def;
		} else {

			try {
				return Double.valueOf(property);
			} catch (Exception e) {
				System.err.println(key + " -> Properties read ex: "
						+ e.getMessage());
			}
		}

		return def;
	}
}
