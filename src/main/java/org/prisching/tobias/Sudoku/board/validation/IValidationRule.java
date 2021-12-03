package org.prisching.tobias.Sudoku.board.validation;

import org.prisching.tobias.Sudoku.board.Board;

public interface IValidationRule {

	public boolean validatePlayfield(Board board);
	
}
