package com.github.brunothg.jellysplush.data.powerup;

import com.github.brunothg.jellysplush.data.Game;
import com.github.brunothg.jellysplush.data.JellyFish;

public interface PowerupStrategy
{

	public void timeElapsed(long elapsedTime, Game game);

	public void playerConsumedItem(JellyFish fish, int x, int y, Game game);
}
