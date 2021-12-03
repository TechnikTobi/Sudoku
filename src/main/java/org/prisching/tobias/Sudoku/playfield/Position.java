package org.prisching.tobias.Sudoku.playfield;

public class Position {

	private int x;
	private int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Position() {
		this(0, 0);
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}

	@Override
	public boolean equals(Object obj) {
		
		if(this == obj) {
			return true;
		}
		
		if(obj instanceof Position) {
			Position objPos = (Position) obj;
			return (this.getX() == objPos.getX() && this.getY() == objPos.getY());
		}
		
		return false;
	}
	
}
