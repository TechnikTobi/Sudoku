package org.prisching.tobias.Sudoku.board.validation;

import java.util.ArrayList;
import java.util.List;

import org.prisching.tobias.Sudoku.board.Board;
import org.prisching.tobias.Sudoku.board.BoardInfoExtractor;
import org.prisching.tobias.Sudoku.board.Field;

public class validateColumnValues implements IBoardValidationRule {

	@Override
	public boolean validatePlayfield(Board playfield) {
		
		BoardInfoExtractor extractor = new BoardInfoExtractor(playfield);
		for(List<Field> column : extractor.getColumns()) {
			List<Integer> seenValues = new ArrayList<Integer>();
			for(Field field : column) {
				if(field.getValue() == 0) continue;
				if(seenValues.contains(field.getValue())) return false;
				seenValues.add(field.getValue());
			}
		}
		
		return true;
	}

}
