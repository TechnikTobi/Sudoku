package org.prisching.tobias.Sudoku.messages.outgoing;

import com.fasterxml.jackson.annotation.JsonGetter;

public abstract class Response {

	private String message = "";
	
	protected void setMessage(String message) {
		this.message = message;
	}
	
	@JsonGetter("Message")
	public String getMessage() {
		return this.message;
	}
	
}
