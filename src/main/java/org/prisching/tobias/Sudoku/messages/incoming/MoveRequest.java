package org.prisching.tobias.Sudoku.messages.incoming;

import org.prisching.tobias.Sudoku.messages.base.NetworkPlayerIdentifier;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.prisching.tobias.Sudoku.messages.base.NetworkGameIdentifier;
import org.prisching.tobias.Sudoku.messages.base.JSONnames;
import org.prisching.tobias.Sudoku.messages.base.NetworkField;

public class MoveRequest extends Request {

	private final NetworkPlayerIdentifier netPlayerID;
	private final NetworkGameIdentifier netGameID;
	private final NetworkField netField;
	
	@JsonCreator
	public MoveRequest(@JsonProperty(value = JSONnames.PLAYER_ID, required = true) NetworkPlayerIdentifier netPlayerID, @JsonProperty(value = JSONnames.GAME_ID, required = true) NetworkGameIdentifier netGameID, @JsonProperty(value = JSONnames.SINGLE_FIELD, required = true) NetworkField netField) {
		this.netPlayerID = netPlayerID;
		this.netGameID = netGameID;
		this.netField = netField;
	}
	
	public NetworkPlayerIdentifier getNetPlayerID() {
		return this.netPlayerID;
	}
	
	public NetworkGameIdentifier getNetGameID() {
		return this.netGameID;
	}
	
	public NetworkField getNetField() {
		return this.netField;
	}
	
	@Override
	public String getPrintString() {
		return 
				this.getClass().getName() + ": "
					+ "'" + JSONnames.PLAYER_ID + "'='" + this.netPlayerID.getIdentifier() + "'"
					+ "'" + JSONnames.GAME_ID + "'='" + this.netGameID.getIdentifier() + "'"
					+ "'" + JSONnames.SINGLE_FIELD + "'='(" + this.netField.getX() + ", " + this.netField.getY() + ", " + this.netField.getValue() + ")'";
	}
	
}
