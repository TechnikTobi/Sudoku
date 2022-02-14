package org.prisching.tobias.Sudoku.messages.validation;

/**
 * Class for validating that a given string only contains 'alpha' values, that is, the ASCII digits from 0 to 9
 */
public class IsAlpha {

	/** A default message in case the check failed */
	private static final String DEFAULT_MESSAGE = "IsAlpha check failed";

	/** The ASCII value for '0' */
	private static final int ASCII_0 = 48;

	/** The ASCII value for '9' */
	private static final int ASCII_9 = 57;

	/**
	 * Checks the given string for only containing digits
	 * @param value The string to check
	 * @param message A message to use in an {@link IllegalArgumentException} in case the check failed, i.e. non-digit
	 *                characters are found
	 * @return The given string if the check runs through without throwing an exception
	 */
	public static String check(String value, String message) {
		if(value.chars().filter(c -> c >= ASCII_0).filter(c -> c <= ASCII_9).count() != value.length()) {
			throw new IllegalArgumentException((message != null) ? message: DEFAULT_MESSAGE);
		}
		return value;
	}
	
}
