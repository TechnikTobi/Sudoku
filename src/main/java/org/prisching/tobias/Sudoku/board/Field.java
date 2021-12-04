package org.prisching.tobias.Sudoku.board;

public class Field {

	private Position pos;
	private int value;
	
	public Field(Position pos, int value) {
		this.pos = pos;
		this.value = value;
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
	
}
