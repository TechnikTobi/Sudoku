package org.prisching.tobias.Sudoku.messages.outgoing;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;

import org.prisching.tobias.Sudoku.messages.base.JSONnames;
import org.prisching.tobias.Sudoku.messages.base.NetworkField;
import org.prisching.tobias.Sudoku.messages.base.PlayerListElement;

public class GameStateResponse extends Response {
	
	private final List<NetworkField> fields;
	private final List<PlayerListElement> players;
	
	public GameStateResponse(List<NetworkField> fields, List<PlayerListElement> players) {
		this.fields = fields;
		this.players = players;
	}
	
	public GameStateResponse(List<NetworkField> fields, List<PlayerListElement> players, String message) {
		this(fields, players);
		this.setMessage(message);
	}
	
	@JsonGetter(JSONnames.FIELDS)
	@JsonInclude(JsonInclude.Include.NON_NULL) 
	public List<NetworkField> getFields() {
		return this.fields;
	}
	
	@JsonGetter(JSONnames.PLAYERS)
	public List<PlayerListElement> getPlayers() {
		return this.players;
	}
	
}
