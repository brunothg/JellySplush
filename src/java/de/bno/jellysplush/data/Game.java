package de.bno.jellysplush.data;

public class Game {

	private PlayGround playground;
	private JellyFish[] jellyFishs;

	public Game(GameController... controllers) {

		this(JellyFish.fromController(controllers));
	}

	public Game(JellyFish... jellyFishs) {

		this(new PlayGround(2 + jellyFishs.length, 2 + jellyFishs.length),
				jellyFishs);
	}

	public Game(PlayGround playground, GameController... controllers) {

		this(playground, JellyFish.fromController(controllers));
	}

	public Game(PlayGround playground, JellyFish... jellyFishs) {

		this.playground = playground;
		this.jellyFishs = jellyFishs;

		initialize();
	}

	/**
	 * Create start configuration with given {@link PlayGround} and
	 * {@link JellyFish}/{@link GameController}
	 */
	public void initialize() {

		playground.clearField();
		createStartPositions();
	}

	public PlayGround getPlayground() {

		return playground;
	}

	public JellyFish[] getJellyFishs() {

		return jellyFishs;
	}

	private void createStartPositions() {

		Position[] startPositions = playground
				.getStartPositions(jellyFishs.length);

		for (int i = 0; i < jellyFishs.length; i++) {

			jellyFishs[i].setPosition(startPositions[i]);
		}
	}

	public void applyModifications(long elapsedTime) {

		for (int i = 0; i < jellyFishs.length; i++) {

			jellyFishs[i].applyModifications(this, elapsedTime);
		}
	}
}
