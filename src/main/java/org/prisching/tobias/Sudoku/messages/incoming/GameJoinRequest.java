package org.prisching.tobias.Sudoku.messages.incoming;

import org.prisching.tobias.Sudoku.messages.base.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class GameJoinRequest {

	private final NetworkPlayerIdentifier netPlayerID;
	private final NetworkGameIdentifier netGameID;

	@JsonCreator
	public GameJoinRequest(@JsonProperty(value = JSONnames.PLAYER_ID, required = true) NetworkPlayerIdentifier netPlayerID, @JsonProperty(value = JSONnames.GAME_ID, required = true) NetworkGameIdentifier netGameID) {
		this.netPlayerID = netPlayerID;
		this.netGameID = netGameID;
	}
	
	public NetworkPlayerIdentifier getNetPlayerID() {
		return this.netPlayerID;
	}
	
	public NetworkGameIdentifier getNetGameID() {
		return this.netGameID;
	}
	
}
