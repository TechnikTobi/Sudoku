package org.prisching.tobias.Sudoku.playfield;

public class SudokuField {

	private Position pos;
	private int value;
	
	public SudokuField(Position pos, int value) {
		this.pos = pos;
		this.value = value;
	}
	
	public SudokuField(int x, int y, int value) {
		this(new Position(x, y), value);
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
