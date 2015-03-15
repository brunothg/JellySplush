package de.bno.jellysplush.gui.game;

import game.engine.control.GameKeyAdapter;
import game.engine.image.ImageUtils;
import game.engine.image.InternalImage;
import game.engine.image.sprite.ColoredSprite;
import game.engine.image.sprite.DefaultSprite;
import game.engine.image.sprite.Sprite;
import game.engine.stage.scene.Scene;
import game.engine.stage.scene.object.AnimatedSceneObject;
import game.engine.stage.scene.object.ImageSceneObject;
import game.engine.stage.scene.object.Point;
import game.engine.time.TimeUtils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.EventListener;

import de.bno.jellysplush.data.Game;
import de.bno.jellysplush.data.JellyFish;
import de.bno.jellysplush.data.PlayGround;
import de.bno.jellysplush.gui.KeyboardController;

public class GameScene implements Scene {

	private static final Color BACKGROUND_COLOR = new Color(222, 222, 222);

	private static final double SPEED = 2.83/* px in sec */;

	private boolean freeMovement = true;

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
		g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
				RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);

		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, width, height);

		recalculateSize(width, height);
		recalculatePositions(elapsedTime);

		paintBoard(g, width, height, elapsedTime);
		paintJellyFish(g, width, height, elapsedTime);
	}

	private void recalculatePositions(long elapsedTime) {

		double movement = TimeUtils.Seconds(elapsedTime) * SPEED;

		JellyFish[] fishs = game.getJellyFishs();

		for (int i = 0; i < fishs.length; i++) {

			JellyFish fish = fishs[i];

			recalculatePosition(fish, elapsedTime, movement);
		}
	}

	private void recalculatePosition(JellyFish fish, long elapsedTime,
			double movement) {
		// TODO recalculatePosition

		double moveX = 0;
		double moveY = 0;

		if (fish.isMovingLeft()) {
			moveX -= movement;
		}

		if (fish.isMovingRight()) {
			moveX += movement;
		}

		if (fish.isMovingUp()) {
			moveY -= movement;
		}

		if (fish.isMovingDown()) {
			moveY += movement;
		}

		fish.setX(fish.getX() + moveX);
		fish.setY(fish.getY() + moveY);

		PlayGround pg = game.getPlayground();

		double x = fish.getX();
		double y = fish.getY();

		if (x <= PlayGround.BORDER_WIDTH || y <= PlayGround.BORDER_WIDTH
				|| x >= pg.getWidth() - PlayGround.BORDER_WIDTH - 1
				|| y >= pg.getHeight() - PlayGround.BORDER_WIDTH - 1) {

			wallCollision(fish);
		}
	}

	private void wallCollision(JellyFish fish) {

		PlayGround pg = game.getPlayground();

		double x = fish.getX();
		double y = fish.getY();

		x = Math.max(PlayGround.BORDER_WIDTH, x);
		x = Math.min(x, pg.getWidth() - PlayGround.BORDER_WIDTH - 1);

		y = Math.max(PlayGround.BORDER_WIDTH, y);
		y = Math.min(y, pg.getHeight() - PlayGround.BORDER_WIDTH - 1);

		fish.setPosition(x, y);
	}

	private void paintJellyFish(Graphics2D g, int width, int height,
			long elapsedTime) {

		JellyFish[] fishs = game.getJellyFishs();

		for (int i = 0; i < jellyAnis.length; i++) {

			JellyFish fish = fishs[i];
			AnimatedSceneObject ani = jellyAnis[i];

			int x;
			int y;

			if (isFreeMovement()) {

				x = (int) Math.round(fish.getX() * ani.getWidth());
				y = (int) Math.round(fish.getY() * ani.getHeight());
			} else {

				x = (int) Math.round(fish.getX()) * (ani.getWidth());
				y = (int) Math.round(fish.getY()) * (ani.getHeight());
			}

			ani.setTopLeftPosition(new Point(x, y));
			ani.paintOnScene(g, elapsedTime);
		}
	}

	private void paintBoard(Graphics2D g, int width, int height,
			long elapsedTime) {

		PlayGround playground = game.getPlayground();

		int tWidth = width / playground.getWidth();
		int tHeight = height / playground.getHeight();

		for (int y = 0; y < playground.getHeight(); y++) {
			for (int x = 0; x < playground.getWidth(); x++) {

				switch (playground.getField(x, y)) {
				case BOX:
					wall.setTopLeftPosition(new Point(x * tWidth, y * tHeight));
					wall.paintOnScene(g, elapsedTime);
					break;
				case JELLY:
					jelly.setTopLeftPosition(new Point(x * tWidth, y * tHeight));
					jelly.paintOnScene(g, elapsedTime);
					break;
				case NAIL:
					nail.setTopLeftPosition(new Point(x * tWidth, y * tHeight));
					nail.paintOnScene(g, elapsedTime);
					break;
				default:
					break;
				}
			}
		}
	}

	private void recalculateSize(int width, int height) {

		PlayGround playground = game.getPlayground();

		int tWidth = width / playground.getWidth();
		int tHeight = height / playground.getHeight();

		wall.setSize(tWidth, tHeight);
		jelly.setSize(tWidth, tHeight);
		nail.setSize(tWidth, tHeight);

		for (int i = 0; i < jellyAnis.length; i++) {

			jellyAnis[i].setSize(tWidth, tHeight);
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

		game = new Game(new PlayGround(21, 21), fish);
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

	public boolean isFreeMovement() {
		return freeMovement;
	}

	public void setFreeMovement(boolean freeMovement) {
		this.freeMovement = freeMovement;
	}
}
