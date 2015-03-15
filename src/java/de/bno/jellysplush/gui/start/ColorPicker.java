package de.bno.jellysplush.gui.start;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import game.engine.stage.scene.object.SceneObject;

public class ColorPicker extends SceneObject {

	private Color color;

	public ColorPicker(Color c) {

		this.setColor(c);
	}

	@Override
	protected void paint(Graphics2D g2d, long elapsed) {

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setColor(Color.BLACK);
		g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);

		g2d.setColor(getColor());
		g2d.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 5, 5);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
