package de.bno.jellysplush.gui.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.EventListener;

import de.bno.jellysplush.data.Game;
import de.bno.jellysplush.data.JellyFish;
import de.bno.jellysplush.data.PlayGround;
import de.bno.jellysplush.gui.KeyboardController;
import game.engine.control.GameKeyAdapter;
import game.engine.image.ImageUtils;
import game.engine.image.InternalImage;
import game.engine.image.sprite.ColoredSprite;
import game.engine.image.sprite.DefaultSprite;
import game.engine.image.sprite.Sprite;
import game.engine.stage.scene.Scene;
import game.engine.stage.scene.object.AnimatedSceneObject;
import game.engine.stage.scene.object.ImageSceneObject;
import game.engine.time.TimeUtils;

public class GameScene implements Scene {

	private GameKeyAdapter keys = new GameKeyAdapter();

	private Game game;

	private AnimatedSceneObject[] jellyAnis;
	private ImageSceneObject wall;
	private ImageSceneObject jelly;
	private ImageSceneObject nail;

	public GameScene(Color c1, Color c2) {

		createGame(c1, c2);
		createSceneObjects(c1, c2);
	}

	@Override
	public void paintScene(Graphics2D g, int width, int height, long elapsedTime) {
		// TODO paint GameScene

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);

		paintBoard(g, width, height, elapsedTime);
	}

	private void paintBoard(Graphics2D g, int width, int height,
			long elapsedTime) {

		PlayGround playground = game.getPlayground();

		int tWidth = width / playground.getWidth();
		int tHeight = height / playground.getHeight();

		wall.setSize(tWidth, tHeight);
		jelly.setSize(tWidth, tHeight);
		nail.setSize(tWidth, tHeight);

		for (int y = 0; y < playground.getHeight(); y++) {
			for (int x = 0; x < playground.getWidth(); x++) {

				switch (playground.getField(x, y)) {
				case BOX:
					wall.setPosition(x * tWidth, y * tHeight);
					wall.paintOnScene(g, elapsedTime);
					break;
				case JELLY:
					jelly.setPosition(x * tWidth, y * tHeight);
					jelly.paintOnScene(g, elapsedTime);
					break;
				case NAIL:
					nail.setPosition(x * tWidth, y * tHeight);
					nail.paintOnScene(g, elapsedTime);
					break;
				default:
					break;
				}
			}
		}
	}

	@Override
	public EventListener[] getEventListeners() {

		return new EventListener[] { keys };
	}

	private void createGame(Color c1, Color c2) {

		KeyboardController controller1 = new KeyboardController(
				KeyboardController.WASD, keys);

		KeyboardController controller2 = new KeyboardController(
				KeyboardController.ARROW, keys);

		JellyFish[] fish = new JellyFish[2];

		fish[0] = new JellyFish(controller1, c1);
		fish[1] = new JellyFish(controller2, c2);

		game = new Game(new PlayGround(20, 20), fish);
	}

	private void createSceneObjects(Color c1, Color c2) {

		Sprite objectSprite = new DefaultSprite(
				ImageUtils.BufferedImage(InternalImage.load("FieldSprite.png")),
				32, 32);

		wall = new ImageSceneObject(objectSprite.getTile(0, 0));
		nail = new ImageSceneObject(objectSprite.getTile(1, 0));
		jelly = new ImageSceneObject(objectSprite.getTile(2, 0));

		jellyAnis = new AnimatedSceneObject[2];
		jellyAnis[0] = new AnimatedSceneObject(new ColoredSprite(
				new DefaultSprite(ImageUtils.BufferedImage(InternalImage
						.load("FigureSprite.png")), 32, 32), c1),
				TimeUtils.NanosecondsOfMilliseconds(100));

		jellyAnis[1] = new AnimatedSceneObject(new ColoredSprite(
				new DefaultSprite(ImageUtils.BufferedImage(InternalImage
						.load("FigureSprite.png")), 32, 32), c2),
				TimeUtils.NanosecondsOfMilliseconds(100));
	}
}
