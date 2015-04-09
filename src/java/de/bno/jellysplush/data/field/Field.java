package de.bno.jellysplush.data.field;

public class Field
{

	private FieldType fieldType;
	private String image;

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

	public String getImage()
	{
		return image;
	}

	public void setImage(String image)
	{
		this.image = image;
	}

}
