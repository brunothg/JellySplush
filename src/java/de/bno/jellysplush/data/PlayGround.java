package de.bno.jellysplush.data;

public class PlayGround {

	public static final int BORDER_WIDTH = 1;

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

		if (width < BORDER_WIDTH + 1 || height < BORDER_WIDTH + 1) {

			throw new IllegalArgumentException(
					"Size to small. Minimum size is " + (BORDER_WIDTH + 1)
							+ "x" + (BORDER_WIDTH + 1) + ". -> " + width + "x"
							+ height);
		}

		this.width = width + (2 * BORDER_WIDTH);
		this.height = height + (2 * BORDER_WIDTH);

		createField();
	}

	private void createField() {

		field = new Field[this.height][this.width];
		clearField();
	}

	public void clearField() {

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

				if (y < BORDER_WIDTH || x < BORDER_WIDTH
						|| y >= height - BORDER_WIDTH
						|| x >= width - BORDER_WIDTH) {

					field[y][x] = Field.BOX;
				} else {

					field[y][x] = Field.EMPTY;
				}
			}
		}
	}

	public Field getField(int x, int y) {

		return field[y][x];
	}

	public boolean isAccessible(int x, int y) {

		return field[y][x] != Field.BOX;
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

	/**
	 * The result of this method maybe unique every time it is called. The
	 * default implementation creates {@link Position}s around the border.
	 */
	public Position[] getStartPositions(int numPositions) {

		if (numPositions > ((width - BORDER_WIDTH * 2) * 2)
				+ ((height - BORDER_WIDTH * 2) * 2) - 4) {
			throw new ArrayIndexOutOfBoundsException("Max positions: "
					+ (((width - BORDER_WIDTH * 2) * 2)
							+ ((height - BORDER_WIDTH * 2) * 2) - 4));
		}

		if (numPositions <= 4) {
			return getCirclePositions(numPositions);
		} else {
			return getBorderPositions(numPositions);
		}
	}

	private Position[] getCirclePositions(int numPositions) {

		Position[] circ = new Position[4];
		circ[0] = new Position(BORDER_WIDTH, BORDER_WIDTH);
		circ[1] = new Position(width - BORDER_WIDTH - 1, width - BORDER_WIDTH
				- 1);
		circ[2] = new Position(width - BORDER_WIDTH - 1, BORDER_WIDTH);
		circ[3] = new Position(BORDER_WIDTH, height - BORDER_WIDTH - 1);

		Position[] ret = new Position[numPositions];
		for (int i = 0; i < ret.length && i < circ.length; i++) {

			ret[i] = circ[i];
		}

		return ret;
	}

	private Position[] getBorderPositions(int numPositions) {

		Position[] pos = new Position[numPositions];

		int index = 0;

		loop: for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {

				boolean isLeftBorder = x == BORDER_WIDTH && y >= BORDER_WIDTH
						&& y < height - BORDER_WIDTH;

				boolean isRightBorder = x == width - BORDER_WIDTH - 1
						&& y >= BORDER_WIDTH && y < height - BORDER_WIDTH;

				boolean isTopBorder = y == BORDER_WIDTH && x >= BORDER_WIDTH
						&& x < width - BORDER_WIDTH;

				boolean isBottomBorder = y == height - BORDER_WIDTH - 1
						&& x >= BORDER_WIDTH && x < width - BORDER_WIDTH;

				if (isLeftBorder || isRightBorder || isTopBorder
						|| isBottomBorder) {

					pos[index] = new Position(x, y);

					index++;
				}

				if (!(index < pos.length)) {
					break loop;
				}
			}
		}

		return pos;
	}
}
