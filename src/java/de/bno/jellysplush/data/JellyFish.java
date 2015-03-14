package de.bno.jellysplush.data;

import java.awt.Color;

public class JellyFish {

	private int x;
	private int y;

	private Controller controller;
	private Color color;

	public JellyFish(Controller controller) {

		this(controller, new Color((int) Math.random() * 255,
				(int) Math.random() * 255, (int) Math.random() * 255));
	}

	public JellyFish(Controller controller, Color c) {

		this(0, 0, controller, c);
	}

	public JellyFish(int x, int y, Controller controller, Color c) {

		if (controller == null) {
			throw new NullPointerException("A controller is required.");
		}

		this.color = c;
		this.controller = controller;
		setPosition(x, y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setPosition(int x, int y) {

		setX(x);
		setY(y);
	}

	public Color getColor() {

		return this.color;
	}

	public boolean isMovingLeft() {

		return controller.isMovingLeft();
	}

	public boolean isMovingRight() {

		return controller.isMovingRight();
	}

	public boolean isMovingUp() {

		return controller.isMovingUp();
	}

	public boolean isMovingDown() {

		return controller.isMovingDown();
	}

	public static JellyFish[] fromController(final Controller... controllers) {

		if (controllers == null) {
			return null;
		}

		JellyFish[] fishes = new JellyFish[controllers.length];

		for (int i = 0; i < fishes.length; i++) {

			fishes[i] = new JellyFish(controllers[i]);
		}

		return fishes;
	}

}
