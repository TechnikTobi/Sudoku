package org.prisching.tobias.Sudoku.messages.outgoing;

import org.prisching.tobias.Sudoku.messages.base.JSONnames;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;

public class ResponseContainer {

	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private final Response response;
	
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	private final String errorMessage;
	
	public ResponseContainer(Response response, String errorMessage) {
		this.response = response;
		this.errorMessage = errorMessage;
	}
	
	public ResponseContainer() {
		this(null, null);
	}
	
	public ResponseContainer(Response response) {
		this(response, null);
	}
	
	public ResponseContainer(String errorMessage) {
		this(null, errorMessage);
	}
	
	@JsonGetter(JSONnames.DATA)
	public Response getResponse() {
		return this.response;
	}
	
	@JsonGetter(JSONnames.ERROR)
	public String getErrorMessage() {
		return this.errorMessage;
	}
}
