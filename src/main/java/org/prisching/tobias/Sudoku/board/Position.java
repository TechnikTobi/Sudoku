package org.prisching.tobias.Sudoku.board;

/**
 * The Position class is a simple data structure for storing the coordinates e.g. of a {@link Field} object
 */
public class Position {

	/** The x coordinate of the position */
	private final int x;

	/** The y coordinate of the position */
	private final int y;

	/**
	 * Constructor for creating a new position
	 * @param x The x coordinate of the new position
	 * @param y The y coordinate of the new position
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructor for creating a new, empty Position
	 */
	public Position() {
		this(0, 0);
	}

	/**
	 * Gets the x coordinate of the position
	 * @return The x coordinate
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Gets the y coordinate of the position
	 * @return The y coordinate
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Method for printing a position. Useful for debugging.
	 */
	public void print() {
		System.out.println("(" + this.x + "," + this.y + ")");
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		return this.x * 11 + this.y * 31;
	}

	/** {@inheritDoc} */
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
