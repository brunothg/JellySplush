package de.bno.jellysplush.data.powerup;

import de.bno.jellysplush.data.JellyFish;

public class SpeedPowerup extends DefaultPowerup
{

	private static double SPEED_PLUS = 0.5;
	private static double MAX_SPEED = 3;

	public SpeedPowerup()
	{

		setImage("speed_plus.png");
	}

	@Override
	public void manipulateOwnJellyFish(JellyFish fish)
	{

		fish.setSpeed(Math.min(fish.getSpeed() + SPEED_PLUS, MAX_SPEED));
	}
}
