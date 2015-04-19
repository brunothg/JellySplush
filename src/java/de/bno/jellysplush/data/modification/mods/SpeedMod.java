package de.bno.jellysplush.data.modification.mods;

import game.engine.time.TimeUtils;
import de.bno.jellysplush.data.Game;
import de.bno.jellysplush.data.JellyFish;
import de.bno.jellysplush.data.modification.DefaultModificator;

public class SpeedMod extends DefaultModificator {

	private static final long TIME_TO_LIVE = TimeUtils.NanosecondsOfSeconds(3);

	private long time = 0;
	private boolean alive = true;

	private double speedModification;
	private double speedPlus;
	private boolean setUp = true;

	public SpeedMod() {

		this(1);
	}

	public SpeedMod(double speedModification) {

		this.speedModification = speedModification;
	}

	@Override
	public boolean isAlive(long elapsedTime) {

		return alive;
	}

	@Override
	public void modifiy(Game game, JellyFish fish, long elapsedTime) {

		if (!alive) {
			return;
		}
		time += elapsedTime;

		// Modification end -> revert speed modification
		if (time > TIME_TO_LIVE) {
			alive = false;

			fish.setSpeed(fish.getSpeed() - speedPlus);
			return;
		}

		// First time called -> apply speed modification
		if (setUp) {
			setUp = false;

			speedPlus = fish.getSpeed() * (speedModification - 1);
			fish.setSpeed(fish.getSpeed() + speedPlus);
		}
	}
}
