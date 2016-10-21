package com.github.brunothg.jellysplush.data.powerup;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.github.brunothg.game.engine.time.TimeUtils;
import com.github.brunothg.jellysplush.Constants;
import com.github.brunothg.jellysplush.Settings;
import com.github.brunothg.jellysplush.data.Game;
import com.github.brunothg.jellysplush.data.JellyFish;
import com.github.brunothg.jellysplush.data.PlayGround;
import com.github.brunothg.jellysplush.data.Position;
import com.github.brunothg.jellysplush.data.field.Field;
import com.github.brunothg.jellysplush.data.field.FieldType;
import com.github.brunothg.jellysplush.data.field.PowerupField;

public class DefaultStrategy implements PowerupStrategy
{

	private static final long POWERUP_CREATION_TIME = Math.abs(
		TimeUtils.NanosecondsOfSeconds(Settings.getInt(Settings.KEY_POWERUP_TIME, Constants.POWERUP_TIME_DEFAULT)));
	private long time = 0;

	@Override
	public void timeElapsed(long elapsedTime, Game game)
	{
		time += elapsedTime;

		if (time > POWERUP_CREATION_TIME)
		{
			time = 0;

			createRandomPowerup(game.getPlayground());
		}
	}

	@Override
	public void playerConsumedItem(JellyFish fish, int x, int y, Game game)
	{
		Field consumedField = game.getPlayground().getField(x, y);
		FieldType fieldType = consumedField.getFieldType();

		switch (fieldType)
		{
			case NAIL:
				createRandomPowerup(game.getPlayground());
			break;
			default:
			break;
		}
	}

	private void createRandomPowerup(PlayGround playground)
	{

		Powerup powerup = PowerupUtils.getRandomPowerups(1)[0];

		List<Position> fields = Arrays.asList(playground.getEmptyFields());
		Collections.shuffle(fields);

		if (fields.size() < 1)
		{
			return;
		}

		Position position = fields.get(0);
		playground.setField((int) position.getX(), (int) position.getY(), new PowerupField(powerup.clone()));
	}
}
