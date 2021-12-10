package org.prisching.tobias.Sudoku.messages.outgoing;

import org.prisching.tobias.Sudoku.messages.base.JSONnames;
import org.prisching.tobias.Sudoku.messages.base.NetworkPlayerIdentifier;

import com.fasterxml.jackson.annotation.JsonGetter;

public class PlayerRegistrationResponse extends Response {

	private NetworkPlayerIdentifier netPlayerID;
	
	public PlayerRegistrationResponse(NetworkPlayerIdentifier netPlayerID) {
		this.netPlayerID = netPlayerID;
	}
	
	public PlayerRegistrationResponse(String message) {
		this.netPlayerID = null;
		this.setMessage(message);
	} 
	
	@JsonGetter(JSONnames.PLAYER_ID)
	public String getPlayerIDstring() {
		return this.netPlayerID.getIdentifier();
	}
	
}
