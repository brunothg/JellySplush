package de.bno.jellysplush.gui.game;

import game.engine.stage.scene.object.SceneObject;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Status extends SceneObject {

	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int MIDDLE = 2;

	private static final Color TEXT_COLOR = Color.BLACK;
	private static final Color BACKGROUND_COLOR = new Color(255, 255, 255, 100);

	private int points = 0;
	private int lifes = 0;

	private int textPosition = LEFT;

	@Override
	protected void paint(Graphics2D g, long elapsedTime) {
		// TODO paint Status

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
				RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		drawString(g);
	}

	private void drawString(Graphics2D g) {

		Font oldFont = g.getFont();

		g.setFont(new Font(oldFont.getName(), oldFont.getStyle(), getHeight()));

		String s = String.format("LP: %d JP: %d", lifes, points);

		FontMetrics metrics = g.getFontMetrics();
		int width = metrics.stringWidth(s);
		int height = metrics.getMaxAscent() + metrics.getMaxDescent();

		while (width > getWidth() || height > getHeight()) {

			oldFont = g.getFont();
			Font newFont = new Font(oldFont.getName(), oldFont.getStyle(),
					oldFont.getSize() - 1);
			g.setFont(newFont);

			metrics = g.getFontMetrics();
			width = metrics.stringWidth(s);
			height = metrics.getAscent() + metrics.getDescent();
		}

		double ratio = 1.0 / (metrics.getMaxAscent() / (double) metrics
				.getMaxDescent());
		double add = height * ratio;

		int x = 0;
		switch (getTextPosition()) {
		case LEFT:
			x = 0;
			break;
		case RIGHT:
			x = (int) (getWidth() - width);
			break;
		case MIDDLE:
		default:
			x = (int) (getWidth() * 0.5 - width * 0.5);
			break;
		}

		int y = (int) (getHeight() * 0.5 + height * 0.5 - add);

		g.setColor(BACKGROUND_COLOR);
		g.fillRoundRect(x - 2, y - metrics.getMaxAscent() - 2, width + 4,
				height + (int) (add * 0.5) + 4, 2, 2);

		g.setColor(TEXT_COLOR);
		g.drawString(s, x, y);
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getLifes() {
		return lifes;
	}

	public void setLifes(int lifes) {
		this.lifes = lifes;
	}

	public int getTextPosition() {
		return textPosition;
	}

	public void setTextPosition(int textPosition) {
		this.textPosition = textPosition;
	}

}
