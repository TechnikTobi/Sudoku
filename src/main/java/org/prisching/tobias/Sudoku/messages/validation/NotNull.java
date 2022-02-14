package org.prisching.tobias.Sudoku.messages.validation;

/**
 * Class for checking that a given object is not null
 */
public class NotNull {

	/** A default message in case the check failed */
	private static final String DEFAULT_MESSAGE = "NotNull check failed";

	/**
	 * Checks the given object for not being null
	 * @param value The object to check for being null
	 * @param message A message to use in an {@link NullPointerException} in case the check failed (= the value is null)
	 * @param <V> The type of the given value
	 * @return The given object if the check runs through without throwing an exception
	 */
	public static <V> V check(V value, String message) {
		if(value == null) throw new NullPointerException((message != null) ? message : DEFAULT_MESSAGE);
		return value;
	}
	
}
