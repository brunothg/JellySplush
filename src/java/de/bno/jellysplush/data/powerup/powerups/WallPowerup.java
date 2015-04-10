package de.bno.jellysplush.data.powerup.powerups;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
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

		List<Position> possiblefields = Arrays.asList(playground
				.getEmptyFields());
		List<Position> fields = new LinkedList<Position>();

		for (Position pos : possiblefields) {

			if (blockedFields(pos, playground) < 1) {

				fields.add(pos);
			}
		}

		if (fields.size() < 1) {
			return;
		}

		Collections.shuffle(fields);

		Position p1 = fields.get(0);

		playground.setFieldType((int) (p1.getX()), (int) (p1.getX()),
				FieldType.BOX);
	}

	private int blockedFields(Position pos, PlayGround playground) {

		int blocked = 0;

		int _x = (int) (pos.getX());
		int _y = (int) (pos.getY());

		for (int y = _y - 1; y < _y + 1; y++) {
			for (int x = _x - 1; x < _x + 1; x++) {
				if (y != _y && x != _x) {

					try {
						switch (playground.getFieldType(x, y)) {
						case BOX:
							blocked++;
						default:
							break;

						}
					} catch (Exception e) {
						blocked++;
					}
				}
			}
		}

		return blocked;
	}

	@Override
	public Powerup clone() {
		return new WallPowerup();
	}
}
