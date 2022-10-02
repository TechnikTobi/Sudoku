package org.prisching.tobias.Sudoku.messages.base;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A network class for transmitting data of {@link org.prisching.tobias.Sudoku.board.Field} objects
 */
public final class NetworkField {

	private final int x;
	private final int y;
	private final int value;
	private final String color;

	/**
	 * The JSON Creator to create a new NetworkField from JSON data
	 * @param x The x coordinate of the field
	 * @param y The y coordinate of the field
	 * @param value The value of the field
	 * @param color The color of the field
	 */
	@JsonCreator
	public NetworkField(
			@JsonProperty(value = JSONnames.POS_X) int x, 
			@JsonProperty(value = JSONnames.POS_Y) int y, 
			@JsonProperty(value = JSONnames.VALUE) int value, 
			@JsonProperty(value = JSONnames.COLOR) String color
	) {
		this.x = x;
		this.y = y;
		this.value = value;
		this.color = color;
	}

	/**
	 * JSON-Getter for the x coordinate of the network field
	 * @return The x coordinate of the field
	 */
	@JsonGetter(JSONnames.POS_X)
	public int getX() {
		return this.x;
	}

	/**
	 * JSON-Getter for the y coordinate of the network field
	 * @return The y coordinate of the field
	 */
	@JsonGetter(JSONnames.POS_Y)
	public int getY() {
		return this.y;
	}

	/**
	 * JSON-Getter for the value of the network field
	 * @return The value of the field
	 */
	@JsonGetter(JSONnames.VALUE)
	public int getValue() {
		return this.value;
	}

	/**
	 * JSON-Getter for the color of the network field
	 * @return The color of the field
	 */
	@JsonGetter(JSONnames.COLOR)
	public String getColor() {
		return this.color;
	}
}
