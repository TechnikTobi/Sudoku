package org.prisching.tobias.Sudoku.messages.base;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class NetworkField {

	private final int x;
	private final int y;
	private final int value;
	private final String color;
	
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
	
	@JsonGetter(JSONnames.POS_X)
	public int getX() {
		return this.x;
	}
	
	@JsonGetter(JSONnames.POS_Y)
	public int getY() {
		return this.y;
	}
	
	@JsonGetter(JSONnames.VALUE)
	public int getValue() {
		return this.value;
	}
	
	@JsonGetter(JSONnames.COLOR)
	public String getColor() {
		return this.color;
	}
}
