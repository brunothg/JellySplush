package de.bno.jellysplush.data.powerup.powerups;

import de.bno.jellysplush.Constants;
import de.bno.jellysplush.data.JellyFish;
import de.bno.jellysplush.data.powerup.DefaultPowerup;

public class SpeedPowerup extends DefaultPowerup
{

	private static double SPEED_PLUS = 0.2;
	private static double MAX_SPEED = Constants.MAX_REL_SPEED;

	public SpeedPowerup()
	{

		super(0);
	}

	@Override
	public void manipulateOwnJellyFish(JellyFish fish)
	{

		fish.setSpeed(Math.min(fish.getSpeed() + SPEED_PLUS, MAX_SPEED));
	}

}
