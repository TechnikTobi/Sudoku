package org.prisching.tobias.Sudoku.board.validation;

import java.util.List;

import org.prisching.tobias.Sudoku.board.Board;
import org.prisching.tobias.Sudoku.board.BoardInfoExtractor;

import java.util.ArrayList;

public class BoardValidator {

	private List<IValidationRule> rules;
	private static BoardValidator validator; 
	
	private BoardValidator() {
		this.rules = new ArrayList<IValidationRule>();
		
		this.rules.add(new validateSize());
		this.rules.add(new validateRowValues());
		this.rules.add(new validateColumnValues());
		this.rules.add(new validateSquareValues());
	}
	
	public boolean validate(Board board) {
		for(IValidationRule rule : this.rules) {
			if(rule.validatePlayfield(board) == false) return false;
		}
		
		return true;
	}
	
	public static BoardValidator getValidator() {
		if(BoardValidator.validator == null) {
			BoardValidator.validator = new BoardValidator();
		}
		return BoardValidator.validator;
	}
	
}
