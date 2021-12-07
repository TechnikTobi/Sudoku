package org.prisching.tobias.Sudoku.messages.base;

import com.fasterxml.jackson.annotation.JsonGetter;

public final class NetworkField {

	private final int x;
	private final int y;
	private final int value;
	private final String color;
	
	public NetworkField(int x, int y, int value, String color) {
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
