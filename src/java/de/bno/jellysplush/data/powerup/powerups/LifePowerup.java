package de.bno.jellysplush.data.powerup.powerups;

import game.engine.time.TimeUtils;
import de.bno.jellysplush.Constants;
import de.bno.jellysplush.Settings;
import de.bno.jellysplush.data.JellyFish;
import de.bno.jellysplush.data.powerup.DefaultPowerup;
import de.bno.jellysplush.data.powerup.Powerup;

public class LifePowerup extends DefaultPowerup {

	private static final double EXTENDED_LIFETIME = 0.2;

	public LifePowerup() {

		super(5);
		setLifetimeOnBoard(Math.round(TimeUtils.NanosecondsOfSeconds(Settings
				.getInt(Settings.KEY_POWERUP_LIFETIME,
						Constants.POWERUP_LIFETIME_DEFAULT))
				* EXTENDED_LIFETIME));
	}

	@Override
	public void manipulateOwnJellyFish(JellyFish fish) {

		fish.setLifes(fish.getLifes() + 1);
	}

	@Override
	public Powerup clone() {

		return new LifePowerup();
	}

}
