package de.bno.jellysplush.data.powerup;

import de.bno.jellysplush.data.Game;
import de.bno.jellysplush.data.JellyFish;

public interface PowerupStrategy
{

	public void timeElapsed(long elapsedTime, Game game);

	public void playerConsumedItem(JellyFish fish, int x, int y, Game game);
}
