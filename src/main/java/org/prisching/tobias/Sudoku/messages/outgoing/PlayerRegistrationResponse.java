package org.prisching.tobias.Sudoku.messages.outgoing;

import org.prisching.tobias.Sudoku.game.player.PlayerID;
import org.prisching.tobias.Sudoku.messages.base.JSONnames;

import com.fasterxml.jackson.annotation.JsonGetter;

public class PlayerRegistrationResponse extends Response {

	private PlayerID id;
	
	public PlayerRegistrationResponse(PlayerID id) {
		this.id = id;
	}
	
	public PlayerRegistrationResponse(String message) {
		this.id = null;
		this.setMessage(message);
	} 
	
	@JsonGetter(JSONnames.PLAYER_ID)
	public String getPlayerIDstring() {
		return this.id.getPlayerID();
	}
	
}
