package de.bno.jellysplush.data.powerup.powerups;

import game.engine.time.TimeUtils;
import de.bno.jellysplush.Constants;
import de.bno.jellysplush.Settings;
import de.bno.jellysplush.data.JellyFish;
import de.bno.jellysplush.data.PlayGround;
import de.bno.jellysplush.data.Position;
import de.bno.jellysplush.data.field.FieldType;
import de.bno.jellysplush.data.powerup.DefaultPowerup;
import de.bno.jellysplush.data.powerup.Powerup;

public class WormholePowerup extends DefaultPowerup {

	private static final double EXTENDED_LIFETIME = 2;

	private Position link;
	private Position own;

	public WormholePowerup(Position own, Position link) {

		super(3);

		this.own = own;
		this.link = link;

		setLifetimeOnBoard(Math.round(TimeUtils.NanosecondsOfSeconds(Settings
				.getInt(Settings.KEY_POWERUP_LIFETIME,
						Constants.POWERUP_LIFETIME_DEFAULT))
				* EXTENDED_LIFETIME));
	}

	@Override
	public void manipulateOwnJellyFish(JellyFish fish) {

		fish.setPosition(link);
	}

	@Override
	public void manipulatePlayGround(PlayGround playground) {

		playground.setFieldType((int) (own.getX()), (int) (own.getY()),
				FieldType.EMPTY);
		playground.setFieldType((int) (link.getX()), (int) (link.getY()),
				FieldType.EMPTY);
	}

	@Override
	public Powerup clone() {

		WormholePowerup wormholePowerup = new WormholePowerup(own, link);

		return wormholePowerup;
	}

	public Position getLink() {
		return link;
	}

	public Position getOwn() {
		return own;
	}

}
