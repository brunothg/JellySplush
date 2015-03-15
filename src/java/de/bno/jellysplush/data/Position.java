package de.bno.jellysplush.data;

public class Position {

	private double x;
	private double y;

	public Position() {
		this(0, 0);
	}

	public Position(int x, int y) {

		this.x = x;
		this.y = y;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}
}
