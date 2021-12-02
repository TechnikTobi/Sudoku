package org.prisching.tobias.Sudoku.playfield;

import java.util.Map;
import java.util.HashMap;

public class Playfield {

	private Map<Position, SudokuField> fields;
	
	public Playfield() {
		this.fields = new HashMap<Position, SudokuField>();
	}
	
	public void addField(SudokuField field) {
		this.fields.put(field.getPos(), field);
	}
	
	public SudokuField getField (Position pos) {
		return this.fields.values().stream().filter(f -> f.getPos().equals(pos)).findFirst().get();
	}
	
}
