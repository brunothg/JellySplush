package de.bno.jellysplush;

import java.io.FileInputStream;
import java.util.Properties;

public class Settings {

	public static final String KEY_MAX_LIFES = "max_lifes";
	public static final String KEY_MAX_POINTS = "max_points";
	public static final String KEY_FREE_MOVEMENT = "enable_free_movement";
	public static final String KEY_SPEED = "speed";

	public static final Properties props = new Properties(
			System.getProperties());

	static {

		try {
			props.load(new FileInputStream("./settings.properties"));
		} catch (Exception e) {
		}
	}

	public static boolean getBoolean(String key, boolean def) {

		String property = props.getProperty(key);

		if (property == null) {

			return def;
		} else {

			try {
				return Boolean.valueOf(property);
			} catch (Exception e) {
			}
		}

		return def;
	}

	public static int getInt(String key, int def) {

		String property = props.getProperty(key);

		if (property == null) {

			return def;
		} else {

			try {
				return Integer.valueOf(property);
			} catch (Exception e) {
			}
		}

		return def;
	}

	public static double getDouble(String key, double def) {

		String property = props.getProperty(key);

		if (property == null) {

			return def;
		} else {

			try {
				return Double.valueOf(property);
			} catch (Exception e) {
			}
		}

		return def;
	}
}
