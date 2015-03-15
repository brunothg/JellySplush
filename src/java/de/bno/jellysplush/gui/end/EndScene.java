package de.bno.jellysplush.gui.end;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

import de.bno.jellysplush.data.JellyFish;
import game.engine.stage.scene.Scene;

public class EndScene implements Scene {

	private ActionListener actionListener;

	public EndScene(JellyFish fish1, JellyFish fish2) {
	}

	@Override
	public void paintScene(Graphics2D g, int width, int height, long elapsedTime) {
		// TODO EndScene

		fireRestartEvent();
	}

	@Override
	public EventListener[] getEventListeners() {
		return null;
	}

	public ActionListener getActionListener() {
		return actionListener;
	}

	public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
	}

	private void fireRestartEvent() {

		if (getActionListener() != null) {
			getActionListener().actionPerformed(
					new ActionEvent(this, ActionEvent.ACTION_PERFORMED,
							"restart"));
		}
	}

}
