package de.bno.jellysplush.data;

public class Field
{

	private FieldType fieldType;

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

}
