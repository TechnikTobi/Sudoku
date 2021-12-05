package org.prisching.tobias.Sudoku.messages.incoming;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerRegistrationRequest extends Request {

	@NotNull
	@Size(min = 3, max = 100)
	private String name;
	
	@JsonCreator
	public PlayerRegistrationRequest(@JsonProperty(value = "playerName", required = true) String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
}
