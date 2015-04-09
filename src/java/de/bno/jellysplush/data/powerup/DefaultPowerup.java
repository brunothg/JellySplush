package de.bno.jellysplush.data.powerup;

import de.bno.jellysplush.data.JellyFish;
import de.bno.jellysplush.data.PlayGround;
import de.bno.jellysplush.data.field.Field;
import de.bno.jellysplush.data.field.FieldType;

public abstract class DefaultPowerup extends Field implements Powerup {

	public DefaultPowerup() {

		super(FieldType.POWERUP);
	}

	@Override
	public void manipulateOwnJellyFish(JellyFish fish) {
	}

	@Override
	public void manipulateOtherJellyFishs(JellyFish... fishs) {
	}

	@Override
	public void manipulatePlayGround(PlayGround playground) {
	}

	@Override
	public int getId() {

		return getImage();
	}

}
