package de.bno.jellysplush;

import game.engine.frame.SwingGameFrame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.bno.jellysplush.data.JellyFish;
import de.bno.jellysplush.gui.game.GameListener;
import de.bno.jellysplush.gui.game.GameScene;
import de.bno.jellysplush.gui.start.StartScene;

public class Controller implements GameListener {

	private SwingGameFrame display;

	public Controller(SwingGameFrame disp) {

		this.display = disp;

		initialize();
	}

	private void initialize() {

		setStartScene();
	}

	private void setStartScene() {

		final StartScene startScene = new StartScene();
		startScene.setActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				setGameScene(startScene.getColor(false),
						startScene.getColor(true));
			}
		});

		display.setScene(startScene);
	}

	private void setGameScene(Color c1, Color c2) {

		System.out.println(String.format("Start game: Left[%s] - Right[%s]",
				c1.toString(), c2.toString()));

		final GameScene gameScene = new GameScene(c1, c2);
		gameScene.setFreeMovement(true);
		gameScene.setGameListener(this);

		display.setScene(gameScene);
	}

	@Override
	public void gameOver(JellyFish fish1, JellyFish fish2) {
		// TODO gameOver

	}

	@Override
	public int getMaxPoints() {

		return 30;
	}

	@Override
	public int getMaxLifes() {

		return 4;
	}
}
