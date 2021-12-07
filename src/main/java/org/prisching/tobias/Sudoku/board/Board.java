package org.prisching.tobias.Sudoku.board;

import java.util.Map;
import java.util.HashMap;

public class Board {

	// Should contain 81 fields with positions from (0,0) to (8,8)
	private Map<Position, Field> fields;
	
	public static final int MAX_X = 9;
	public static final int MAX_Y = 9;
	
	public Board() {
		this.fields = new HashMap<Position, Field>();
		for(int y = 0; y < MAX_Y; y++) {
			for(int x = 0; x < MAX_X; x++) {
				Position newPos = new Position(x, y);
				this.fields.put(newPos, new Field(newPos));
			}
		}
	}
	
	public Board(Board board) {
		this.fields = new HashMap<Position, Field>();
		for(int y = 0; y < MAX_Y; y++) {
			for(int x = 0; x < MAX_X; x++) {
				Position newPos = new Position(x, y);
				this.fields.put(newPos, new Field(newPos, board.getField(newPos).getValue()));
			}
		}
	}
	
	public Field getField (Position pos) {
		return this.fields.get(pos);
	}
	
	public Map<Position, Field> getAllFields() {
		return this.fields;
	}
	
}
