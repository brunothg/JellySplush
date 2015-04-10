package de.bno.jellysplush.data.powerup.powerups;

import de.bno.jellysplush.Constants;
import de.bno.jellysplush.data.JellyFish;
import de.bno.jellysplush.data.powerup.DefaultPowerup;
import de.bno.jellysplush.data.powerup.Powerup;

public class SlowPowerup extends DefaultPowerup {

	private static double SPEED_PLUS = -0.25;
	private static double MIN_SPEED = Constants.MIN_REL_SPEED;

	public SlowPowerup() {

		super(1);
	}

	@Override
	public void manipulateOwnJellyFish(JellyFish fish) {

		fish.setSpeed(Math.max(MIN_SPEED, fish.getSpeed() + SPEED_PLUS));
	}

	@Override
	public Powerup clone() {
		return new SlowPowerup();
	}
}
