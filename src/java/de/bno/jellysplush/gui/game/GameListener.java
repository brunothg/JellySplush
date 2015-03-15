package de.bno.jellysplush.gui.game;

import java.util.EventListener;

import de.bno.jellysplush.data.JellyFish;

public interface GameListener extends EventListener {

	public int getMaxPoints();

	public int getMaxLifes();

	public void gameOver(JellyFish fish1, JellyFish fish2);
}
