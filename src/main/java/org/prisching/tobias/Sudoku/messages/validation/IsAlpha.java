package org.prisching.tobias.Sudoku.messages.validation;

import java.util.stream.Collectors;

public class IsAlpha {

	private static final String DEFAULT_MESSAGE = "IsAlpha check failed";
	
	private static final int ASCI_0 = 48;
	private static final int ASCI_9 = 57;
	
	public static String check(String value, String message) {
		if(value.chars().filter(c -> c >= ASCI_0).filter(c -> c <= ASCI_9).count() != value.length()) {
			throw new IllegalArgumentException((message != null) ? message: DEFAULT_MESSAGE);
		}
		return value;
	}
	
}
