package org.prisching.tobias.Sudoku.board.solver;

import java.util.List;
import java.util.stream.IntStream;

import org.prisching.tobias.Sudoku.board.Board;
import org.prisching.tobias.Sudoku.board.BoardInfoExtractor;
import org.prisching.tobias.Sudoku.board.Field;
import org.prisching.tobias.Sudoku.board.Position;
import org.prisching.tobias.Sudoku.board.validation.BoardValidator;

/**
 * The BoardSolver provides a static method for solving a given Sudoku Board
 */
public class BoardSolver {

	/**
	 * Tries to find all possible solutions for a given Sudoku
	 * @param board The board of the Sudoku to solve
	 * @return The number of solutions that were found
	 */
	public static int solve(Board board) {
		return BoardSolver.recursiveSolve(board);
	}

	/**
	 * Recursively solves a Sudoku by filling in empty fields of the board as long as the Sudoku is valid
	 * @param board The board of the Sudoku to solve
	 * @return The number of solutions that were found
	 */
	private static int recursiveSolve(Board board) {
	
		BoardInfoExtractor extractor = new BoardInfoExtractor(board);

		// If the board is invalid, return 0. If it is already full (i.e. no more fields to fill) return 1
		if(!BoardValidator.validate(board)) return 0;
		if(extractor.isFull()) return 1;

		// Get the position of the first empty field you can find
		Position emptyPosition = board.getAllFields().values()
				.stream()
				.filter(f -> f.getValue() == Field.EMPTY_FIELD_VALUE)
				.map(Field::getPos)
				.findFirst().orElse(null);

		assert(emptyPosition != null);

		int returnValue = 0;
		List<Integer> numbers = IntStream.range(1, 10).boxed().sorted().toList();

		for(Integer value : numbers) {

			// If the value is already present within the row, column or square, use the next number
			if(extractor.getRow(emptyPosition).stream().anyMatch(f -> f.getValue() == value)) continue;
			if(extractor.getColumn(emptyPosition).stream().anyMatch(f -> f.getValue() == value)) continue;
			if(extractor.getSquare(emptyPosition).stream().anyMatch(f -> f.getValue() == value)) continue;

			// If all these checks pass, set the field accordingly and continue solving the board by making a recursive
			// call to solve the next empty field
			board.getField(emptyPosition).setValue(value);
			returnValue += BoardSolver.recursiveSolve(board);

			// Reset the value for the field once done
			board.getField(emptyPosition).setValue(Field.EMPTY_FIELD_VALUE);

		}

		return returnValue;
	}
	
}
