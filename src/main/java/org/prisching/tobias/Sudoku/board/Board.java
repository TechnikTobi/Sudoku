package org.prisching.tobias.Sudoku.board;

import java.util.Map;
import java.util.HashMap;

/**
 * The Board class is the data structure for holding a Sudoku board with all of its fields
 */
public class Board {

	/** The list of all fields with positions ranging from (0,0) to (8,8) */
	private final Map<Position, Field> fields;

	/** Number of fields in the x direction */
	public static final int MAX_X = 9;

	/** Number of fields in the y direction */
	public static final int MAX_Y = 9;

	/**
	 * Constructor for creating a new, empty board
	 */
	public Board() {
		this.fields = new HashMap<>();
		for(int y = 0; y < MAX_Y; y++) {
			for(int x = 0; x < MAX_X; x++) {
				Position newPos = new Position(x, y);
				this.fields.put(newPos, new Field(newPos));
			}
		}
	}

	/**
	 * Constructor for copying another board
	 * @param board The board to base the copy on
	 */
	public Board(Board board) {
		this.fields = new HashMap<>();
		for(int y = 0; y < MAX_Y; y++) {
			for(int x = 0; x < MAX_X; x++) {
				Position newPos = new Position(x, y);
				this.fields.put(newPos, new Field(newPos, board.getField(newPos).getValue()));
			}
		}
	}

	/**
	 * Gets the field at the given position via a {@link Position} object
	 * @param pos The position to get the field for
	 * @return The field at the specified position
	 */
	public Field getField (Position pos) {
		return this.fields.get(pos);
	}

	/**
	 * Gets the list of all fields of the board
	 * @return The list of all {@link Field} objects of the board
	 */
	public Map<Position, Field> getAllFields() {
		return this.fields;
	}
	
}
