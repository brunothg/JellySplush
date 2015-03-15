package de.bno.jellysplush.gui;

import java.awt.event.KeyEvent;

import game.engine.control.GameKeyAdapter;
import de.bno.jellysplush.data.GameController;

public class KeyboardController extends GameController {

	public static final int WASD = 0x01;
	public static final int ARROW = 0x02;

	private int type;
	private GameKeyAdapter keys;

	public KeyboardController(int type, GameKeyAdapter keys) {

		this.type = type;
		this.keys = keys;
	}

	private int getLeftKey() {

		switch (type) {
		case WASD:
			return KeyEvent.VK_A;
		case ARROW:
			return KeyEvent.VK_LEFT;

		}

		return 0;
	}

	private int getRightKey() {

		switch (type) {
		case WASD:
			return KeyEvent.VK_D;
		case ARROW:
			return KeyEvent.VK_RIGHT;

		}

		return 0;
	}

	private int getUpKey() {

		switch (type) {
		case WASD:
			return KeyEvent.VK_W;
		case ARROW:
			return KeyEvent.VK_UP;

		}

		return 0;
	}

	private int getDownKey() {

		switch (type) {
		case WASD:
			return KeyEvent.VK_S;
		case ARROW:
			return KeyEvent.VK_DOWN;

		}

		return 0;
	}

	@Override
	public boolean isMovingLeft() {

		super.setMovingLeft(keys.isKeyDown(getLeftKey()));

		return super.isMovingLeft();
	}

	@Override
	public boolean isMovingRight() {

		super.setMovingRight(keys.isKeyDown(getRightKey()));

		return super.isMovingRight();
	}

	@Override
	public boolean isMovingDown() {

		super.setMovingDown(keys.isKeyDown(getDownKey()));

		return super.isMovingDown();
	}

	@Override
	public boolean isMovingUp() {

		super.setMovingUp(keys.isKeyDown(getUpKey()));

		return super.isMovingUp();
	}
}
