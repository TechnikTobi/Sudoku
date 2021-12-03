package org.prisching.tobias.Sudoku.board.validation;

import org.prisching.tobias.Sudoku.board.Board;
import org.prisching.tobias.Sudoku.board.BoardInfoExtractor;

public class validateSize implements IValidationRule {

	@Override
	public boolean validatePlayfield(Board playfield) {
		BoardInfoExtractor extractor = new BoardInfoExtractor(playfield);
		return (
			extractor.getMaxXPos().equals(extractor.getMaxYPos()) &&
			extractor.getMaxXPos().getX() == (Board.MAX_X -1) &&
			extractor.getMaxYPos().getY() == (Board.MAX_Y -1)
		);
	}
	
}
