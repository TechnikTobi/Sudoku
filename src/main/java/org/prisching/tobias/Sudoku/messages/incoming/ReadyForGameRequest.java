package org.prisching.tobias.Sudoku.messages.incoming;

import org.prisching.tobias.Sudoku.messages.base.JSONnames;
import org.prisching.tobias.Sudoku.messages.base.NetworkGameIdentifier;
import org.prisching.tobias.Sudoku.messages.base.NetworkPlayerIdentifier;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReadyForGameRequest extends GameJoinRequest {

	@JsonCreator
	public ReadyForGameRequest(@JsonProperty(value = JSONnames.PLAYER_ID, required = true) NetworkPlayerIdentifier netPlayerID, @JsonProperty(value = JSONnames.GAME_ID, required = true) NetworkGameIdentifier netGameID) {
		super(netPlayerID, netGameID);
	}

}
