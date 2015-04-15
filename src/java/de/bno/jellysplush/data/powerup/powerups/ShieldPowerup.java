package de.bno.jellysplush.data.powerup.powerups;

import de.bno.jellysplush.data.JellyFish;
import de.bno.jellysplush.data.modification.mods.InvincibleMod;
import de.bno.jellysplush.data.powerup.DefaultPowerup;
import de.bno.jellysplush.data.powerup.Powerup;

public class ShieldPowerup extends DefaultPowerup {

	public ShieldPowerup() {

		super(7);
	}

	@Override
	public void manipulateOwnJellyFish(JellyFish fish) {

		fish.addModification(new InvincibleMod());
	}

	@Override
	public Powerup clone() {
		return new ShieldPowerup();
	}
}
