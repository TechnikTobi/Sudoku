package org.prisching.tobias.Sudoku.messages.outgoing;

import org.prisching.tobias.Sudoku.game.player.PlayerID;

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
	
	@JsonGetter("PlayerID")
	public String getPlayerIDstring() {
		return this.id.getPlayerID();
	}
	
}
