package de.bno.jellysplush.data.powerup;

import de.bno.jellysplush.Constants;
import de.bno.jellysplush.data.JellyFish;

public class SlowPowerup extends DefaultPowerup
{

	private static double SPEED_PLUS = -0.15;
	private static double MIN_SPEED = Constants.MIN_REL_SPEED;

	public SlowPowerup()
	{

		super(1);
	}

	@Override
	public void manipulateOwnJellyFish(JellyFish fish)
	{

		fish.setSpeed(Math.max(MIN_SPEED, fish.getSpeed() + SPEED_PLUS));
	}
}
