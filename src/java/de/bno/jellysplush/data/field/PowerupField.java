package de.bno.jellysplush.data.field;

import de.bno.jellysplush.data.powerup.Powerup;

public class PowerupField extends Field
{

	private Powerup powerup;

	public PowerupField(Powerup powerup)
	{

		this.powerup = powerup;
		setFieldType(FieldType.POWERUP);
	}

	public Powerup getPowerup()
	{
		return powerup;
	}

	public void setPowerup(Powerup powerup)
	{
		this.powerup = powerup;
	}

}
