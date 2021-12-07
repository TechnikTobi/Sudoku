package org.prisching.tobias.Sudoku.messages.outgoing;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;

public abstract class Response {

	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private String message = null;
	
	protected void setMessage(String message) {
		this.message = message;
	}
	
	@JsonGetter("Message")
	public String getMessage() {
		return this.message;
	}
	
}
