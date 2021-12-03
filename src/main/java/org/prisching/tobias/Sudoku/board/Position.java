package org.prisching.tobias.Sudoku.board;

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
	
	public void print() {
		System.out.println("(" + this.x + "," + this.y + ")");
	}

	@Override
	public int hashCode() {
		return this.x * 11 + this.y * 31;
	}
	
	@Override
	public boolean equals(Object obj) {		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Position casted = (Position) obj;
		return (this.x == casted.x && this.y == casted.y);

	}
	
}
