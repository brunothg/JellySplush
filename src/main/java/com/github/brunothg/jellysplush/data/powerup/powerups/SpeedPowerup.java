package com.github.brunothg.jellysplush.data.powerup.powerups;

import com.github.brunothg.jellysplush.data.JellyFish;
import com.github.brunothg.jellysplush.data.modification.mods.SpeedMod;
import com.github.brunothg.jellysplush.data.powerup.DefaultPowerup;
import com.github.brunothg.jellysplush.data.powerup.Powerup;

public class SpeedPowerup extends DefaultPowerup
{

	private static double SPEED_MANIPULATION_FACTOR = 2;

	public SpeedPowerup()
	{

		super(0);
	}

	@Override
	public void manipulateOwnJellyFish(JellyFish fish)
	{

		fish.addModification(new SpeedMod(SPEED_MANIPULATION_FACTOR));
	}

	@Override
	public Powerup clone()
	{

		return new SpeedPowerup();
	}

}
