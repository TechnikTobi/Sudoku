package org.prisching.tobias.Sudoku.messages.validation;

import java.util.Collection;

public class NotNullNorEmpty {

	private static final String DEFAULT_MESSAGE = "NotEmpty check failed"; 
	
	public static String check(String value, String message) {
		NotNull.check(value, message);
		if(value.isBlank()) throwException(message);
		return value;
	}
	
	public static <V> Collection<V> check(Collection<V> value, String message) {
		NotNull.check(value, message);
		if(value.isEmpty()) throwException(message);
		return value;
	}
	
	private static void throwException(String message) {
		throw new IllegalArgumentException((message != null) ? message : DEFAULT_MESSAGE);
	}
	
}
