package com.github.brunothg.jellysplush.data.field;

public enum FieldType {

	EMPTY(' '), JELLY('°'), NAIL('^'), BOX('X'), POWERUP('P'), JELLYFISH('J');

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
