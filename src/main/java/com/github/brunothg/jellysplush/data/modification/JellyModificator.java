package com.github.brunothg.jellysplush.data.modification;

import com.github.brunothg.jellysplush.data.Game;
import com.github.brunothg.jellysplush.data.JellyFish;

public interface JellyModificator
{

	public boolean isAlive(long elapsedTime);

	public void modifiy(Game game, JellyFish fish, long elapsedTime);

}
