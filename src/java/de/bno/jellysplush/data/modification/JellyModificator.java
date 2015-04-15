package de.bno.jellysplush.data.modification;

import de.bno.jellysplush.data.Game;
import de.bno.jellysplush.data.JellyFish;

public interface JellyModificator {

	public boolean isAlive(long elapsedTime);

	public void modifiy(Game game, JellyFish fish, long elapsedTime);

	public int getImageId();
}
