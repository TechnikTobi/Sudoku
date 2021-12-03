package org.prisching.tobias.Sudoku.playfield;

import java.util.Map;
import java.util.HashMap;

public class Playfield {

	// Should contain 81 fields with positions from (0,0) to (8,8)
	private Map<Position, SudokuField> fields;
	
	public static final int MAX_X = 9;
	public static final int MAX_Y = 9;
	
	public Playfield() {
		this.fields = new HashMap<Position, SudokuField>();
	}
	
	public void addField(SudokuField field) {
		this.fields.put(field.getPos(), field);
	}
	
	public SudokuField getField (Position pos) {
		return this.fields.values().stream().filter(f -> f.getPos().equals(pos)).findFirst().get();
	}
	
	public Map<Position, SudokuField> getAllFields() {
		return this.fields;
	}
	
}
