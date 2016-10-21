package com.github.brunothg.jellysplush.data;

import java.util.Arrays;

public abstract class GameController
{

	public static final int DIRECTION_LEFT = 0x00;
	public static final int DIRECTION_RIGHT = 0x01;
	public static final int DIRECTION_UP = 0x02;
	public static final int DIRECTION_DOWN = 0x03;

	/**
	 * [Left, Right, Up, Down]
	 */
	private boolean[] movement = new boolean[4];

	public GameController()
	{

		Arrays.fill(movement, false);
	}

	public boolean isMovingLeft()
	{

		return movement[DIRECTION_LEFT];
	}

	public void setMovingLeft(boolean moving)
	{

		movement[DIRECTION_LEFT] = moving;
	}

	public boolean isMovingRight()
	{

		return movement[DIRECTION_RIGHT];
	}

	public void setMovingRight(boolean moving)
	{

		movement[DIRECTION_RIGHT] = moving;
	}

	public boolean isMovingUp()
	{

		return movement[DIRECTION_UP];
	}

	public void setMovingUp(boolean moving)
	{

		movement[DIRECTION_UP] = moving;
	}

	public boolean isMovingDown()
	{

		return movement[DIRECTION_DOWN];
	}

	public void setMovingDown(boolean moving)
	{

		movement[DIRECTION_DOWN] = moving;
	}
}
