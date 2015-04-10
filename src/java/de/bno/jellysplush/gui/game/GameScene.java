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
import java.util.Arrays;
import java.util.Collections;
import java.util.EventListener;
import java.util.List;

import de.bno.jellysplush.Constants;
import de.bno.jellysplush.Settings;
import de.bno.jellysplush.data.Game;
import de.bno.jellysplush.data.JellyFish;
import de.bno.jellysplush.data.PlayGround;
import de.bno.jellysplush.data.Position;
import de.bno.jellysplush.data.field.Field;
import de.bno.jellysplush.data.field.FieldType;
import de.bno.jellysplush.data.powerup.Powerup;
import de.bno.jellysplush.data.powerup.PowerupStrategy;
import de.bno.jellysplush.gui.KeyboardController;

public class GameScene implements Scene {

	private static final int MAX_POINTS_DEFAULT = 30;
	private static final int MAX_LIFES_DEFAULT = 4;

	private static final Color BACKGROUND_COLOR = Settings.getColor(
			Settings.KEY_BACKGROUND_COLOR,
			Constants.PLAYGROUND_BACKGROUND_COLOR_DEFAULT);

	private static final double SPEED = Settings.getDouble(Settings.KEY_SPEED,
			5)/* px in sec */;

	private boolean freeMovement = false;

	private GameKeyAdapter keys = new GameKeyAdapter();

	private Game game;
	private GameListener gameListener;
	private PowerupStrategy powerupStrategy;

	private AnimatedSceneObject[] jellyAnis;
	private ImageSceneObject wall;
	private ImageSceneObject jelly;
	private ImageSceneObject nail;
	private Sprite powerups;

	private Status status = new Status();

	private boolean isGameOver = false;

	public GameScene(Color c1, Color c2, PowerupStrategy strategy) {

		createGame(c1, c2);
		createSceneObjects(c1, c2);
		powerups = new DefaultSprite(ImageUtils.BufferedImage(InternalImage
				.load("PowerupSprite.png")), Constants.TILE_WIDTH,
				Constants.TILE_HEIGHT);
		powerupStrategy = strategy;
	}

	@Override
	public void paintScene(Graphics2D g, int width, int height, long elapsedTime) {

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
				RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);

		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, width, height);

		recalculateSize(width, height);
		checkPowerupStatus(elapsedTime);
		applyModifications(elapsedTime);
		updatePositions(elapsedTime);
		checkForItemCollision();
		updateJellyFields();
		powerupStrategy.timeElapsed(elapsedTime, game);

		paintBoard(g, width, height, elapsedTime);
		paintJellyFish(g, width, height, elapsedTime);
		paintStatus(g, width, height);

		if (isGameOver) {

			return;
		}

		checkPoints();
		checkLifes();
	}

	private void updateJellyFields() {

		PlayGround playground = game.getPlayground();

		for (int y = 0; y < playground.getHeight(); y++) {
			for (int x = 0; x < playground.getWidth(); x++) {

				if (playground.getFieldType(x, y) == FieldType.JELLYFISH) {

					playground.setFieldType(x, y, FieldType.EMPTY);
				}
			}
		}

		JellyFish[] jellyFishs = game.getJellyFishs();
		for (int i = 0; i < jellyFishs.length; i++) {
			JellyFish fish = jellyFishs[i];
			playground.setFieldType((int) Math.round(fish.getX()),
					(int) Math.round(fish.getY()), FieldType.JELLYFISH);
		}
	}

	private void updatePositions(long elapsedTime) {

		recalculatePositions(elapsedTime);
		checkForFishCollision();
	}

	private void checkPowerupStatus(long elapsedTime) {

		PlayGround playground = game.getPlayground();

		for (int y = 0; y < playground.getHeight(); y++) {
			for (int x = 0; x < playground.getWidth(); x++) {

				Field field = playground.getField(x, y);

				if (field.getFieldType() == FieldType.POWERUP
						&& field instanceof Powerup) {

					Powerup powerup = (Powerup) field;

					if (!powerup.isAlive(elapsedTime)) {

						playground.setField(x, y, new Field(FieldType.EMPTY));
					}
				}
			}
		}
	}

	private void applyModifications(long elapsedTime) {

		game.applyModifications(elapsedTime);
	}

	private void checkLifes() {

		int maxLifes = getMaxLifes();

		JellyFish[] fishs = game.getJellyFishs();

		for (int i = 0; i < fishs.length; i++) {

			JellyFish fish = fishs[i];

			if (-fish.getLifes() >= maxLifes) {

				gameOver(i != 1);
				break;
			}
		}
	}

	private int getMaxLifes() {
		return (getGameListener() == null) ? MAX_LIFES_DEFAULT
				: getGameListener().getMaxLifes();
	}

	private void checkPoints() {

		int maxPoints = getMaxPoints();

		JellyFish[] fishs = game.getJellyFishs();

		for (int i = 0; i < fishs.length; i++) {

			JellyFish fish = fishs[i];

			if (fish.getPoints() >= maxPoints) {

				gameOver(i == 1);
				break;
			}
		}
	}

	private int getMaxPoints() {
		return (getGameListener() == null) ? MAX_POINTS_DEFAULT
				: getGameListener().getMaxPoints();
	}

	private void paintStatus(Graphics2D g, int width, int height) {

		JellyFish[] fishs = game.getJellyFishs();

		int _width = (int) (width * (2.0 / 5.0));
		int _height = (height / game.getPlayground().getHeight())
				* PlayGround.BORDER_WIDTH;

		int _y = 0;

		int _x1 = (int) (width * (0.25 / 5.0));
		int _x2 = width - _width - _x1;

		status.setSize(_width, _height);

		status.setLifes(getMaxLifes() + fishs[0].getLifes());
		status.setPoints(fishs[0].getPoints());
		status.setPosition(_x1, _y);
		status.paintOnScene(g, 0);

		status.setLifes(getMaxLifes() + fishs[1].getLifes());
		status.setPoints(fishs[1].getPoints());
		status.setPosition(_x2, _y);
		status.paintOnScene(g, 0);
	}

	private void gameOver(boolean rightIsWiner) {

		isGameOver = true;
		System.out.println("Game Over");

		if (getGameListener() == null) {

			System.exit(-1);
		}

		JellyFish[] fishs = game.getJellyFishs();

		getGameListener().gameOver(fishs[0], fishs[1], rightIsWiner);
	}

	private void checkForItemCollision() {

		PlayGround pg = game.getPlayground();
		JellyFish[] fishs = game.getJellyFishs();

		int jellyCount = 0;
		int nailCount = 0;

		for (int i = 0; i < jellyAnis.length; i++) {

			JellyFish fish = fishs[i];

			int posX = (int) Math.round(fish.getX());
			int posY = (int) Math.round(fish.getY());

			FieldType fieldType = pg.getFieldType(posX, posY);

			if (fieldType == FieldType.JELLY || fieldType == FieldType.NAIL
					|| fieldType == FieldType.POWERUP) {

				powerupStrategy.playerConsumedItem(fish, posX, posY, game);
			}

			switch (fieldType) {
			case NAIL:
				if (!fish.isInvincible()) {
					fish.setLifes(fish.getLifes() - 1);
				}
				pg.setFieldType(posX, posY, FieldType.EMPTY);
				nailCount++;
				System.out.println("Nail!!!");
				break;
			case JELLY:
				fish.setPoints(fish.getPoints() + 1);
				pg.setFieldType(posX, posY, FieldType.EMPTY);
				jellyCount++;
				System.out.println("Jelly!!!");
				break;
			case POWERUP:
				jellyFetchesPowerup(getField(posX, posY), fish);
				pg.setFieldType(posX, posY, FieldType.EMPTY);
				System.out.println("Powerup!!!");
				break;
			default:
				break;
			}
		}

		updateJellyAndNails(jellyCount, nailCount);
	}

	private void jellyFetchesPowerup(Field field, JellyFish ownFish) {

		if (field instanceof Powerup == false) {
			return;
		}

		Powerup powerup = (Powerup) field;

		JellyFish[] jellyFishs = game.getJellyFishs();
		JellyFish[] otherFishs = new JellyFish[jellyFishs.length - 1];

		int index = 0;
		for (int i = 0; i < jellyFishs.length; i++) {

			if (jellyFishs[i] != ownFish) {

				otherFishs[index] = jellyFishs[i];
				index++;
			}
		}

		powerup.manipulateOwnJellyFish(ownFish);
		powerup.manipulateOtherJellyFishs(otherFishs);
		powerup.manipulatePlayGround(game.getPlayground());
	}

	private Field getField(int posX, int posY) {

		return game.getPlayground().getField(posX, posY);
	}

	private void updateJellyAndNails(int jellyCount, int nailCount) {

		for (int i = 0; i < jellyCount; i++) {

			generateNewJelly();
			generateNewNail();
		}

		for (int i = 0; i < nailCount; i++) {

			generateNewNail();
		}
	}

	private void generateNewNail() {

		generateNewField(FieldType.NAIL);
	}

	private void generateNewJelly() {

		boolean success = generateNewField(FieldType.JELLY);

		if (!success) {

			PlayGround playground = game.getPlayground();

			for (int y = 0; y < playground.getHeight(); y++) {
				for (int x = 0; x < playground.getWidth(); x++) {

					switch (playground.getFieldType(x, y)) {
					case EMPTY:
					case JELLY:
					case NAIL:
					case POWERUP:
					default:
						playground.setFieldType(x, y, FieldType.JELLY);
						break;

					}
				}
			}
		}
	}

	private boolean generateNewField(FieldType type) {

		PlayGround pg = game.getPlayground();

		List<Position> fields = Arrays.asList(pg.getEmptyFields());
		Collections.shuffle(fields);

		if (fields.size() > 0) {

			Position pos = fields.get(0);
			pg.setFieldType((int) (pos.getX()), (int) (pos.getX()), type);

			return true;
		}

		return false;
	}

	private void recalculatePositions(long elapsedTime) {

		JellyFish[] fishs = game.getJellyFishs();

		for (int i = 0; i < fishs.length; i++) {

			JellyFish fish = fishs[i];

			double movement = TimeUtils.Seconds(elapsedTime)
					* (SPEED * fish.getSpeed());

			recalculatePosition(fish, elapsedTime, movement);

			AnimatedSceneObject ani = jellyAnis[i];

			syncPos(fish, ani);
		}

	}

	private void syncPos(JellyFish fish, AnimatedSceneObject ani) {

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
	}

	private void checkForFishCollision() {

		for (int i = 0; i < jellyAnis.length; i++) {

			do {
			} while (checkCollisionFor(i));
		}

	}

	private boolean checkCollisionFor(int col) {

		boolean anyCollision = false;

		AnimatedSceneObject fish = jellyAnis[col];

		for (int i = 0; i < jellyAnis.length; i++) {

			AnimatedSceneObject ani = jellyAnis[i];
			if (fish == ani) {
				continue;
			}

			if (ani.collides(fish)) {

				handleCollision(col, i);

				anyCollision = true;
			}
		}

		return anyCollision;
	}

	private void handleCollision(int col1, int col2) {

		AnimatedSceneObject ani1 = jellyAnis[col1];
		AnimatedSceneObject ani2 = jellyAnis[col2];

		JellyFish fish1 = game.getJellyFishs()[col1];
		JellyFish fish2 = game.getJellyFishs()[col2];

		double px_X1 = 1.0 / ani1.getWidth();
		double px_Y1 = 1.0 / ani1.getHeight();

		double px_X2 = 1.0 / ani2.getWidth();
		double px_Y2 = 1.0 / ani2.getHeight();

		do {

			boolean rightIsRight = fish1.getX() > fish2.getX();
			boolean rightIsOver = fish1.getY() < fish2.getY();

			double x = fish1.getX();
			double y = fish1.getY();
			{
				double pX = 0;
				double pY = 0;

				if (pX == 0 && pY == 0) {

					pY = (rightIsOver) ? -px_Y1 : px_Y1;
					pX = (rightIsRight) ? px_X1 : -px_X1;
				}

				fish1.setPosition(x + pX, y + pY);
			}

			x = fish2.getX();
			y = fish2.getY();
			{
				double pX = 0;
				double pY = 0;

				if (pX == 0 && pY == 0) {

					pY = (rightIsOver) ? px_Y2 : -px_Y2;
					pX = (rightIsRight) ? -px_X2 : px_X2;
				}

				fish2.setPosition(x + pX, y + pY);
			}

			syncPos(fish1, ani1);
			syncPos(fish2, ani2);

			wallCollision(fish1);
			wallCollision(fish2);
		} while (ani1.collides(ani2));
	}

	private void recalculatePosition(JellyFish fish, long elapsedTime,
			double movement) {

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

		for (int i = 0; i < jellyAnis.length; i++) {

			AnimatedSceneObject ani = jellyAnis[i];

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

				switch (playground.getFieldType(x, y)) {
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
				case POWERUP:
					paintPowerup(playground.getField(x, y), g, elapsedTime,
							new Point(x * tWidth, y * tHeight), tWidth, tHeight);
					break;
				default:
					break;
				}
			}
		}
	}

	private void paintPowerup(Field powerup, Graphics2D g, long elapsedTime,
			Point point, int tWidth, int tHeight) {

		int imageIndex = powerup.getImage();

		try {

			Graphics2D gt = (Graphics2D) g.create(point.getX(), point.getY(),
					tWidth, tHeight);

			powerups.drawTile(gt, imageIndex, 0, tWidth, tHeight);

			gt.dispose();
		} catch (Exception e) {
			g.setColor(Color.RED);
			g.fillRoundRect(point.getX(), point.getY(), tWidth, tHeight,
					(int) (tWidth * 0.1), (int) (tHeight * 0.1));
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

		game = new Game(new PlayGround(Constants.PLAYGROUND_WIDTH,
				Constants.PLAYGROUND_HEIGHT), fish);
	}

	private void createSceneObjects(Color c1, Color c2) {

		Sprite objectSprite = new DefaultSprite(
				ImageUtils.BufferedImage(InternalImage.load("FieldSprite.png")),
				Constants.TILE_WIDTH, Constants.TILE_HEIGHT);

		wall = new ImageSceneObject(objectSprite.getTile(0, 0));
		nail = new ImageSceneObject(objectSprite.getTile(1, 0));
		jelly = new ImageSceneObject(objectSprite.getTile(2, 0));

		jellyAnis = new AnimatedSceneObject[2];
		jellyAnis[0] = new AnimatedSceneObject(new ColoredSprite(
				new DefaultSprite(ImageUtils.BufferedImage(InternalImage
						.load("FigureSprite.png")), Constants.TILE_WIDTH,
						Constants.TILE_HEIGHT), c1),
				TimeUtils.NanosecondsOfMilliseconds(100));

		jellyAnis[1] = new AnimatedSceneObject(new ColoredSprite(
				new DefaultSprite(ImageUtils.BufferedImage(InternalImage
						.load("FigureSprite.png")), Constants.TILE_WIDTH,
						Constants.TILE_HEIGHT), c2),
				TimeUtils.NanosecondsOfMilliseconds(100));
	}

	public boolean isFreeMovement() {
		return freeMovement;
	}

	public void setFreeMovement(boolean freeMovement) {
		this.freeMovement = freeMovement;
	}

	public GameListener getGameListener() {
		return gameListener;
	}

	public void setGameListener(GameListener gameListener) {
		this.gameListener = gameListener;
	}
}
