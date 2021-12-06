package org.prisching.tobias.Sudoku.messages.incoming.rules;

import org.prisching.tobias.Sudoku.messages.incoming.*;

public interface IRequestValidationRule {

	public boolean validatePlayerRegistration(Request request);

	
	
}
