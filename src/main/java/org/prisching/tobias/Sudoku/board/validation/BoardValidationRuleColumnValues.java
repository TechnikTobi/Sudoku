package org.prisching.tobias.Sudoku.board.validation;

import java.util.ArrayList;
import java.util.List;

import org.prisching.tobias.Sudoku.board.Board;
import org.prisching.tobias.Sudoku.board.BoardInfoExtractor;
import org.prisching.tobias.Sudoku.board.Field;

/**
 * Validates a {@link Board} object by checking if the values within each column of the Sudoku are unique
 */
public class BoardValidationRuleColumnValues implements IBoardValidationRule {

	/** {@inheritDoc} */
	@Override
	public boolean validateBoard(Board board) {
		
		BoardInfoExtractor extractor = new BoardInfoExtractor(board);

		for(List<Field> column : extractor.getColumns()) {
			List<Integer> seenValues = new ArrayList<>();

			for(Field field : column) {
				if(field.getValue() == 0) continue;
				if(seenValues.contains(field.getValue())) return false;

				seenValues.add(field.getValue());
			}
		}
		
		return true;
	}

}
