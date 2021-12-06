package org.prisching.tobias.Sudoku.board.validation;

import org.prisching.tobias.Sudoku.board.Board;

public interface IBoardValidationRule {

	public boolean validatePlayfield(Board board);
	
}
