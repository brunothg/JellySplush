package de.bno.jellysplush.data.field;

public class Field
{

	private FieldType fieldType;
	private int image = -1;

	public Field()
	{

		this(FieldType.EMPTY);
	}

	public Field(FieldType fieldType)
	{

		this.fieldType = fieldType;
	}

	public FieldType getFieldType()
	{
		return fieldType;
	}

	public void setFieldType(FieldType fieldType)
	{
		this.fieldType = fieldType;
	}

	public int getImage()
	{
		return image;
	}

	public void setImage(int image)
	{
		this.image = image;
	}

}
