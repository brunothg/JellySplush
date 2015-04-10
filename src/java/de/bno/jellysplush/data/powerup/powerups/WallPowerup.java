package de.bno.jellysplush.data.powerup.powerups;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import de.bno.jellysplush.data.PlayGround;
import de.bno.jellysplush.data.Position;
import de.bno.jellysplush.data.field.FieldType;
import de.bno.jellysplush.data.powerup.DefaultPowerup;
import de.bno.jellysplush.data.powerup.Powerup;

public class WallPowerup extends DefaultPowerup {

	public WallPowerup() {

		super(4);
	}

	@Override
	public void manipulatePlayGround(PlayGround playground) {

		List<Position> fields = Arrays.asList(playground.getEmptyFields());
		if (fields.size() < 1) {
			return;
		}

		Collections.shuffle(fields);

		Position p1 = fields.get(0);

		playground.setFieldType((int) (p1.getX()), (int) (p1.getX()),
				FieldType.BOX);
	}

	@Override
	public Powerup clone() {
		return new WallPowerup();
	}
}
