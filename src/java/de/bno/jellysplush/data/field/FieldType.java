package de.bno.jellysplush.data.field;

public enum FieldType {

	EMPTY(' '), JELLY('°'), NAIL('^'), BOX('X'), POWERUP('P');

	private char charRepresentation;

	private FieldType(char c)
	{

		this.charRepresentation = c;
	}

	public char getCharRepresentation()
	{

		return charRepresentation;
	}
}
