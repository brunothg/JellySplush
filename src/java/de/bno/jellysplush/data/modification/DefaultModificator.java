package de.bno.jellysplush.data.modification;

import de.bno.jellysplush.data.Game;
import de.bno.jellysplush.data.JellyFish;

public abstract class DefaultModificator implements JellyModificator {

	private int imageId;

	public DefaultModificator(int id) {
		imageId = id;
	}

	@Override
	public void modifiy(Game game, JellyFish fish, long elapsedTime) {
	}

	@Override
	public int getImageId() {
		return imageId;
	}
}
