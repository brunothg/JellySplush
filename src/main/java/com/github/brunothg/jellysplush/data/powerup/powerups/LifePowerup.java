package com.github.brunothg.jellysplush.data.powerup.powerups;

import com.github.brunothg.game.engine.time.TimeUtils;
import com.github.brunothg.jellysplush.Constants;
import com.github.brunothg.jellysplush.Settings;
import com.github.brunothg.jellysplush.data.JellyFish;
import com.github.brunothg.jellysplush.data.powerup.DefaultPowerup;
import com.github.brunothg.jellysplush.data.powerup.Powerup;

public class LifePowerup extends DefaultPowerup
{

	private static final double EXTENDED_LIFETIME = 0.2;

	public LifePowerup()
	{

		super(5);
		setLifetimeOnBoard(Math.round(TimeUtils.NanosecondsOfSeconds(
			Settings.getInt(Settings.KEY_POWERUP_LIFETIME, Constants.POWERUP_LIFETIME_DEFAULT)) * EXTENDED_LIFETIME));
	}

	@Override
	public void manipulateOwnJellyFish(JellyFish fish)
	{

		fish.setLifes(fish.getLifes() + 1);
	}

	@Override
	public Powerup clone()
	{

		return new LifePowerup();
	}

}
