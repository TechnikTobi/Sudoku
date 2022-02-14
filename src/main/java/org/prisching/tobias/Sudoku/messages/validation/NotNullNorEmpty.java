package org.prisching.tobias.Sudoku.messages.validation;

import java.util.Collection;

/**
 * Class for validating that a given string or collection is neither null nor blank/empty
 */
public class NotNullNorEmpty {

	/** A default message in case the check failed */
	private static final String DEFAULT_MESSAGE = "NotEmpty check failed";

	/**
	 * Checks the given string for not being null and not being blank
	 * @param value The string to check
	 * @param message A message to use in an {@link NullPointerException} or {@link IllegalArgumentException} in case
	 *                the check failed, i.e. the object is null or, if not null, the string is blank
	 * @return The string if the check runs through without throwing an exception
	 */
	public static String check(String value, String message) {
		NotNull.check(value, message);
		if(value.isBlank()) throwException(message);
		return value;
	}

	/**
	 * Checks the given collection for not being null and not being empty
	 * @param value The collection to check
	 * @param message A message to use in an {@link NullPointerException} or {@link IllegalArgumentException} in case
	 *                the check failed, i.e. the object is null or, if not null, the collection is empty
	 * @param <V> The type of the objects in the given collection
	 * @return The collection if the check runs through without throwing an exception
	 */
	public static <V> Collection<V> check(Collection<V> value, String message) {
		NotNull.check(value, message);
		if(value.isEmpty()) throwException(message);
		return value;
	}

	/**
	 * A helper function to throw an {@link IllegalArgumentException}
	 * @param message The message to use in the exception
	 */
	private static void throwException(String message) {
		throw new IllegalArgumentException((message != null) ? message : DEFAULT_MESSAGE);
	}
	
}
