package org.prisching.tobias.Sudoku.messages.incoming;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerRegistrationRequest extends Request {

	private static final String PLAYER_NAME = "PlayerName";
	
	@NotNull
	@Size(min = 3, max = 100)
	private final String playerName;
	
	@JsonCreator
	public PlayerRegistrationRequest(@JsonProperty(value = PLAYER_NAME, required = true) String name) {
		this.playerName = name;
	}
	
	public String getPlayerName() {
		return this.playerName;
	}

	@Override
	@JsonIgnore
	public String getPrintString() {
		return this.getClass().getName() + ": '" + PLAYER_NAME + "'='" + this.playerName + "'";
	}
	
}
