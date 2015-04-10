package de.bno.jellysplush.data.powerup.powerups;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import de.bno.jellysplush.data.PlayGround;
import de.bno.jellysplush.data.Position;
import de.bno.jellysplush.data.field.PowerupField;
import de.bno.jellysplush.data.powerup.DefaultPowerup;
import de.bno.jellysplush.data.powerup.Powerup;

public class TransporterPowerup extends DefaultPowerup {

	public TransporterPowerup() {

		super(2);
	}

	@Override
	public void manipulatePlayGround(PlayGround playground) {

		List<Position> fields = Arrays.asList(playground.getEmptyFields());
		if (fields.size() < 2) {
			return;
		}

		Collections.shuffle(fields);

		Position p1 = fields.get(0);
		Position p2 = fields.get(1);

		WormholePowerup w1 = new WormholePowerup(p1, p2);
		WormholePowerup w2 = new WormholePowerup(p2, p1);

		playground.setField((int) (p1.getX()), (int) (p1.getY()),
				new PowerupField(w1));
		playground.setField((int) (p2.getX()), (int) (p2.getY()),
				new PowerupField(w2));
	}

	@Override
	public Powerup clone() {
		return new TransporterPowerup();
	}
}
