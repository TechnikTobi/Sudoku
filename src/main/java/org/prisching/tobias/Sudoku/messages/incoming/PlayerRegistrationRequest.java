package org.prisching.tobias.Sudoku.messages.incoming;

import org.prisching.tobias.Sudoku.messages.base.JSONnames;
import org.prisching.tobias.Sudoku.messages.validation.NotNullNorEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class PlayerRegistrationRequest extends Request {
	
	private final String playerName;
	
	@JsonCreator
	public PlayerRegistrationRequest(@JsonProperty(value = JSONnames.PLAYER_NAME, required = true) String name) {
		this.playerName = NotNullNorEmpty.check(name, "Player name should not be null nor empty");
	}
	
	public String getPlayerName() {
		return this.playerName;
	}

	@Override
	@JsonIgnore
	public String getPrintString() {
		return this.getClass().getName() + ": '" + JSONnames.PLAYER_NAME + "'='" + this.playerName + "'";
	}
	
}
