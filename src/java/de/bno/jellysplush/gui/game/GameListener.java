package de.bno.jellysplush.gui.game;

import java.util.EventListener;

public interface GameListener extends EventListener {

	public int getMaxPoints();

	public int getMaxLifes();

	public void gameOver(int pointsLeft, int pointsRight, int lifesLeft,
			int lifesRight);
}
