package de.bno.jellysplush.data;

public class PlayGround {

	private int width;
	private int height;

	private Field[][] field;

	/**
	 * Create a new playground. A border around this field will be created, so
	 * that the given width and height will be increased by two.
	 * 
	 * @param width
	 *            Width of the playable area
	 * @param height
	 *            Height of the playable area
	 */
	public PlayGround(int width, int height) {

		if (width < 2 || height < 2) {

			throw new IllegalArgumentException(
					"Size to small. Minimum size is 2x2. -> " + width + "x"
							+ height);
		}

		this.width = width + 2;
		this.height = height + 2;

		createField();
	}

	private void createField() {

		field = new Field[this.height][this.width];
		clearField();
	}

	private void clearField() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

				if (y == 0 || x == 0 || y == height - 1 || x == width - 1) {

					field[y][x] = Field.BOX;
				} else {

					field[y][x] = Field.EMPTY;
				}
			}
		}
	}

	public Field getField(int x, int y) {

		return field[x][y];
	}

	public boolean isAccessible(int x, int y) {

		return field[x][y] != Field.BOX;
	}

	public int getWidth() {

		return this.width;
	}

	public int getHeight() {

		return this.height;
	}

	@Override
	public String toString() {

		String fieldString = "";

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

				fieldString += String.format(" %s ",
						"" + field[y][x].getCharRepresentation());
			}

			if (y < height - 1) {
				fieldString += "\n";
			}
		}

		return fieldString;
	}
}
