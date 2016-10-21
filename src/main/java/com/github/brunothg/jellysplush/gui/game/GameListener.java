package com.github.brunothg.jellysplush.gui.game;

import java.util.EventListener;

import com.github.brunothg.jellysplush.data.JellyFish;

public interface GameListener extends EventListener
{

	public int getMaxPoints();

	public int getMaxLifes();

	public void gameOver(JellyFish fish1, JellyFish fish2, boolean rightIsWiner);
}
