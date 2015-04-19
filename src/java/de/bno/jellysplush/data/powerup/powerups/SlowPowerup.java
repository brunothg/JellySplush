package de.bno.jellysplush.data.powerup.powerups;

import de.bno.jellysplush.data.JellyFish;
import de.bno.jellysplush.data.modification.mods.SpeedMod;
import de.bno.jellysplush.data.powerup.DefaultPowerup;
import de.bno.jellysplush.data.powerup.Powerup;

public class SlowPowerup extends DefaultPowerup {

	private static double SPEED_MANIPULATION_FACTOR = 0.5;

	public SlowPowerup() {

		super(1);
	}

	@Override
	public void manipulateOwnJellyFish(JellyFish fish) {

		fish.addModification(new SpeedMod(SPEED_MANIPULATION_FACTOR));
	}

	@Override
	public Powerup clone() {
		return new SlowPowerup();
	}
}
