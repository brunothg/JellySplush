package de.bno.jellysplush.data;

public enum Field {

	EMPTY(' '), JELLY('°'), NAIL('^'), BOX('X');

	private char charRepresentation;

	private Field(char c) {

		this.charRepresentation = c;
	}

	public char getCharRepresentation() {

		return charRepresentation;
	}
}
