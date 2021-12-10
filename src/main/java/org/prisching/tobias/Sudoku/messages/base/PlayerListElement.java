package org.prisching.tobias.Sudoku.messages.base;

import com.fasterxml.jackson.annotation.JsonGetter;

public class PlayerListElement {
	
	private final String name;
	private final String color;
	private final int points;
	
	public PlayerListElement(String name, String color, int points) {
		this.name = name;
		this.color = color;
		this.points = points;
	}
	
	@JsonGetter(JSONnames.PLAYER_NAME)
	public String getName() {
		return this.name;
	}
	
	@JsonGetter(JSONnames.POINTS)
	public int getPoints() {
		return this.points;
	}
	
	@JsonGetter(JSONnames.COLOR)
	public String getColor() {
		return this.color;
	}
	
}