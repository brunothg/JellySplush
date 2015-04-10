package de.bno.jellysplush.data.powerup.powerups;

import de.bno.jellysplush.data.JellyFish;
import de.bno.jellysplush.data.modification.mods.DrunkMod;
import de.bno.jellysplush.data.powerup.DefaultPowerup;
import de.bno.jellysplush.data.powerup.Powerup;

public class DrunkPowerup extends DefaultPowerup {

	public DrunkPowerup() {

		super(6);
	}

	@Override
	public void manipulateOtherJellyFishs(JellyFish... fishs) {

		for (int i = 0; i < fishs.length; i++) {

			fishs[i].addModification(new DrunkMod());
		}
	}

	@Override
	public Powerup clone() {
		return new DrunkPowerup();
	}
}
