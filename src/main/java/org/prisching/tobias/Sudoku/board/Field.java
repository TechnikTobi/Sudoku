package org.prisching.tobias.Sudoku.board;

import org.prisching.tobias.Sudoku.game.player.PlayerColor;

public class Field {

	private Position pos;
	private int value;
	private PlayerColor color;
	
	public Field(Position pos, int value) {
		this.pos = pos;
		this.value = value;
		this.color = PlayerColor.getDefaultColor();
	}
	
	public Field(Position pos) {
		this(pos, 0);
	}
	
	public Field(int x, int y, int value) {
		this(new Position(x, y), value);
	}
	
	public Field(int x, int y) {
		this(x, y, 0);
	}

	public Position getPos() {
		return this.pos;
	}

	public int getValue() {
		return this.value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public PlayerColor getColor() {
		return this.color;
	}
	
	public void setColor(PlayerColor color) {
		this.color = color;
	}
	
}
