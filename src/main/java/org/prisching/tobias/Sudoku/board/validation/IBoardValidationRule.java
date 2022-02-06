package org.prisching.tobias.Sudoku.board.validation;

import org.prisching.tobias.Sudoku.board.Board;

/**
 * The interface for a rule to validate a {@link Board} object
 */
public interface IBoardValidationRule {

	/**
	 * Validates a {@link Board} according to the specific rule of the implementing class
	 * @param board The {@link Board} object to validate
	 * @return Whether or not the board is valid according to the implementing rule
	 */
	boolean validateBoard(Board board);
	
}
