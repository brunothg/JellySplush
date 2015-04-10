package de.bno.jellysplush.data;

import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.bno.jellysplush.data.modification.JellyModificator;

public class JellyFish {

	private Position pos = new Position();

	private GameController controller;
	private Color color;

	private int lifes;
	private int points;
	private double speed = 1.0;
	private boolean invincible = false;

	private List<JellyModificator> mods = new LinkedList<JellyModificator>();

	public JellyFish(GameController controller) {

		this(controller, new Color((int) Math.random() * 255,
				(int) Math.random() * 255, (int) Math.random() * 255));
	}

	public JellyFish(GameController controller, Color c) {

		this(0, 0, controller, c);
	}

	public JellyFish(int x, int y, GameController controller, Color c) {

		if (controller == null) {
			throw new NullPointerException("A controller is required.");
		}

		this.color = c;
		this.controller = controller;
		setPosition(x, y);
		setLifes(0);
		setPoints(0);
	}

	public void addModification(JellyModificator mod) {

		mods.add(mod);
	}

	public void removeModification(JellyModificator mod) {

		mods.remove(mod);
	}

	public void applyModifications(Game game, long elapsedTime) {

		Iterator<JellyModificator> iterator = mods.iterator();

		while (iterator.hasNext()) {

			JellyModificator mod = iterator.next();

			if (!mod.isAlive(elapsedTime)) {

				iterator.remove();
			}

			mod.modifiy(game, this, elapsedTime);
		}
	}

	public double getX() {
		return pos.getX();
	}

	public void setX(double x) {
		pos.setX(x);
	}

	public double getY() {
		return pos.getY();
	}

	public void setY(double y) {
		pos.setY(y);
	}

	public void setPosition(double x, double y) {

		setX(x);
		setY(y);
	}

	public void setPosition(Position p) {

		setPosition(p.getX(), p.getY());
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

	public static JellyFish[] fromController(
			final GameController... controllers) {

		if (controllers == null) {
			return null;
		}

		JellyFish[] fishes = new JellyFish[controllers.length];

		for (int i = 0; i < fishes.length; i++) {

			fishes[i] = new JellyFish(controllers[i]);
		}

		return fishes;
	}

	public int getLifes() {
		return lifes;
	}

	public void setLifes(int lifes) {
		this.lifes = lifes;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public boolean isInvincible() {
		return invincible;
	}

	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
	}
}
