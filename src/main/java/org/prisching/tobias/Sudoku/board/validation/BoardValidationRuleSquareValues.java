package org.prisching.tobias.Sudoku.board.validation;

import java.util.ArrayList;
import java.util.List;

import org.prisching.tobias.Sudoku.board.Board;
import org.prisching.tobias.Sudoku.board.BoardInfoExtractor;
import org.prisching.tobias.Sudoku.board.Field;

public class BoardValidationRuleSquareValues implements IBoardValidationRule {

	@Override
	public boolean validateBoard(Board board) {
		BoardInfoExtractor extractor = new BoardInfoExtractor(board);
		for(List<Field> square : extractor.getSquares()) {
			List<Integer> seenValues = new ArrayList<>();
			for(Field field : square) {
				if(field.getValue() == 0) continue;
				if(seenValues.contains(field.getValue())) return false;
				seenValues.add(field.getValue());
			}
		}

		return true;
	}

}
