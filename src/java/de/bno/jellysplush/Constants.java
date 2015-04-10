package de.bno.jellysplush;

import java.awt.Color;

public class Constants {

	// DEFAULT Werte werden nicht zwangsläufig genutzt

	public static final int MAX_POINTS_DEFAULT = 30;
	public static final int MAX_LIFES_DEFAULT = 4;

	public static final Color PLAYGROUND_BACKGROUND_COLOR_DEFAULT = Color.BLACK;

	public static final Color[] USER_COLORS_DEFAULT = new Color[] {
			new Color(0, 153, 255), Color.YELLOW, Color.ORANGE,
			new Color(148, 0, 211), new Color(204, 0, 204), Color.WHITE };

	public static final int POWERUP_TIME_DEFAULT = 15;
	public static final int POWERUP_LIFETIME_DEFAULT = 5;

	// Konstanten die den ablauf auf jeden fall beeinflussen
	public static final int PLAYGROUND_WIDTH = Settings.getInt(
			Settings.KEY_PLAYGROUND_WIDTH, 21);
	public static final int PLAYGROUND_HEIGHT = Settings.getInt(
			Settings.KEY_PLAYGROUND_HEIGHT, PLAYGROUND_WIDTH);

	public static final int TILE_WIDTH = 32;
	public static final int TILE_HEIGHT = TILE_WIDTH;

	public static final double MAX_REL_SPEED = 3.0;
	public static final double MIN_REL_SPEED = 0.1;

	public static final Color COUNTDOWN_COLOR = Color.BLACK;
	public static final Color START_COLOR_1 = new Color(235, 229, 48);
	public static final Color START_COLOR_2 = new Color(255, 248, 24);

	public static final long POWERUP_LIFETIME = Settings.getInt(
			Settings.KEY_POWERUP_LIFETIME, POWERUP_LIFETIME_DEFAULT);

	public static final String WINDOW_TITLE = "JellySplush";

}
