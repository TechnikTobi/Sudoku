package org.prisching.tobias.Sudoku.messages.validation;

public class NotNull {

	private static final String DEFAULT_MESSAGE = "NotNull check failed"; 
	
	public static <V> V check(V value, String message) {
		if(value == null) throw new NullPointerException((message != null) ? message : DEFAULT_MESSAGE);
		return value;
	}
	
}
