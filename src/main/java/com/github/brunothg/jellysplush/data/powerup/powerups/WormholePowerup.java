package com.github.brunothg.jellysplush.data.powerup.powerups;

import com.github.brunothg.game.engine.time.TimeUtils;
import com.github.brunothg.jellysplush.Constants;
import com.github.brunothg.jellysplush.Settings;
import com.github.brunothg.jellysplush.data.JellyFish;
import com.github.brunothg.jellysplush.data.PlayGround;
import com.github.brunothg.jellysplush.data.Position;
import com.github.brunothg.jellysplush.data.field.FieldType;
import com.github.brunothg.jellysplush.data.powerup.DefaultPowerup;
import com.github.brunothg.jellysplush.data.powerup.Powerup;

public class WormholePowerup extends DefaultPowerup
{

	private static final double EXTENDED_LIFETIME = 2;

	private Position link;
	private Position own;

	public WormholePowerup(Position own, Position link)
	{

		super(3);

		this.own = own;
		this.link = link;

		setLifetimeOnBoard(Math.round(TimeUtils.NanosecondsOfSeconds(
			Settings.getInt(Settings.KEY_POWERUP_LIFETIME, Constants.POWERUP_LIFETIME_DEFAULT)) * EXTENDED_LIFETIME));
	}

	@Override
	public void manipulateOwnJellyFish(JellyFish fish)
	{

		fish.setPosition(link);
	}

	@Override
	public void manipulatePlayGround(PlayGround playground)
	{

		playground.setFieldType((int) (own.getX()), (int) (own.getY()), FieldType.EMPTY);
		playground.setFieldType((int) (link.getX()), (int) (link.getY()), FieldType.EMPTY);
	}

	@Override
	public Powerup clone()
	{

		WormholePowerup wormholePowerup = new WormholePowerup(own, link);

		return wormholePowerup;
	}

	public Position getLink()
	{
		return link;
	}

	public Position getOwn()
	{
		return own;
	}

}
