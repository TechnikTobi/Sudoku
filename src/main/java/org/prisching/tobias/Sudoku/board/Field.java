package org.prisching.tobias.Sudoku.board;

import org.prisching.tobias.Sudoku.game.player.PlayerColor;

/**
 * The Field class stores the data for a single field of a Sudoku board.
 */
public class Field {

	/** The value of an empty field */
	public static final int EMPTY_FIELD_VALUE = 0;

	/** The position of the field */
	private final Position pos;

	/** The value of the field */
	private int value;

	/** The color of the field */
	private PlayerColor color;

	/**
	 * Constructor for creating a new field
	 * @param pos The position of the new field as {@link Position} object
	 * @param value The value of the new field
	 */
	public Field(Position pos, int value) {
		this.pos = pos;
		this.value = value;
		this.color = PlayerColor.getDefaultColor();
	}

	/**
	 * Constructor for creating a new field with the empty value
	 * @param pos The position of the new field as {@link Position} object
	 */
	public Field(Position pos) {
		this(pos, EMPTY_FIELD_VALUE);
	}

	/**
	 * Constructor for creating a new field
	 * @param x The x coordinate of the field
	 * @param y The y coordinate of the field
	 * @param value The value of the field
	 */
	public Field(int x, int y, int value) {
		this(new Position(x, y), value);
	}

	/**
	 * Gets the position of the field
	 * @return The fields position
	 */
	public Position getPos() {
		return this.pos;
	}

	/**
	 * Gets the value of the field
	 * @return The fields value
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * Gets the color of the player
	 * @return The {@link PlayerColor} object of the player
	 */
	public PlayerColor getColor() {
		return this.color;
	}

	/**
	 * Sets the value of the field
	 * @param value The new value the field should be set to
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * Sets the color of the field.
	 * @param color The color as a {@link PlayerColor} object
	 */
	public void setColor(PlayerColor color) {
		this.color = color;
	}
	
}
