package org.prisching.tobias.Sudoku.board.validation;

import java.util.ArrayList;
import java.util.List;

import org.prisching.tobias.Sudoku.board.Board;

/**
 * The BoardValidator uses a set of {@link IBoardValidationRule} objects to check if a given {@link Board} is valid
 */
public class BoardValidator {

	/** The list of rules to use for validation */
	private static final List<IBoardValidationRule> rules = new ArrayList<>();

	/**
	 * Fills the list of rules with {@link IBoardValidationRule} objets to use for validation boards
	 */
	private static void setRules() {
		rules.add(new BoardValidationRuleSize());
		rules.add(new BoardValidationRuleRowValues());
		rules.add(new BoardValidationRuleColumnValues());
		rules.add(new BoardValidationRuleSquareValues());
	}

	/**
	 * Validate a {@link Board} object according to the rules in the validators list
	 * @param board The {@link Board} object to validate
	 * @return Whether the given Sudoku board is valid or not
	 */
	public static boolean validate(Board board) {

		// If there aren't any rules yet, set them up
		if(BoardValidator.rules.isEmpty()) BoardValidator.setRules();

		for(IBoardValidationRule rule : BoardValidator.rules) {
			if(!rule.validateBoard(board)) return false;
		}
		
		return true;
	}
	
}
