package org.prisching.tobias.Sudoku.messages.incoming;

import org.prisching.tobias.Sudoku.messages.base.JSONnames;
import org.prisching.tobias.Sudoku.messages.base.NetworkPlayerIdentifier;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class GameCreationRequest extends Request {

	private final NetworkPlayerIdentifier netPlayerID;
	private final String gameName;
	private final int difficulty;

	@JsonCreator
	public GameCreationRequest(@JsonProperty(value = JSONnames.PLAYER_ID, required = true) NetworkPlayerIdentifier netPlayerID, @JsonProperty(value = JSONnames.GAME_NAME, required = true) String gameName, @JsonProperty(value = JSONnames.DIFFICULTY, required = true) int difficulty) {
		this.netPlayerID = netPlayerID;
		this.gameName = gameName;
		this.difficulty = difficulty;
	}

	public NetworkPlayerIdentifier getPlayerID() {
		return this.netPlayerID;
	}

	public String getGameName() {
		return this.gameName;
	}

	public int getDifficulty() {
		return this.difficulty;
	}

	@Override
	@JsonIgnore
	public String getPrintString() {
		return 
			this.getClass().getName() + ": "
				+ "'" + JSONnames.PLAYER_ID + "'='" + this.netPlayerID.getIdentifier() + "'"
				+ "'" + JSONnames.GAME_NAME + "'='" + this.gameName + "'"
				+ "'" + JSONnames.DIFFICULTY + "'='" + this.difficulty + "'";
	}
}
