package org.prisching.tobias.Sudoku.board.validation;

import java.util.List;

import org.prisching.tobias.Sudoku.board.Board;

import java.util.ArrayList;

public class BoardValidator {

	private List<IBoardValidationRule> rules;
	private static BoardValidator validator; 
	
	private BoardValidator() {
		this.rules = new ArrayList<IBoardValidationRule>();
		
		this.rules.add(new validateSize());
		this.rules.add(new validateRowValues());
		this.rules.add(new validateColumnValues());
		this.rules.add(new validateSquareValues());
	}
	
	public boolean validate(Board board) {
		for(IBoardValidationRule rule : this.rules) {
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
