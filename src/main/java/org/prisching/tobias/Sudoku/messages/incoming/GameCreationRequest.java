package org.prisching.tobias.Sudoku.messages.incoming;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;

import org.prisching.tobias.Sudoku.game.player.PlayerID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GameCreationRequest extends Request {

	private static final String PLAYER_ID = "PlayerID";
	private static final String GAME_NAME = "GameName";
	private static final String DIFFICULTY = "Difficulty";

	@NotNull
	@Size(min = 1)
	private final String playerIDstring;

	@NotNull
	@Size(min = 1)
	private final String gameName;

	@Min(0)
	@Max(81)
	private final int difficulty;

	@JsonCreator
	public GameCreationRequest(@JsonProperty(value = PLAYER_ID, required = true) String playerIDstring, @JsonProperty(value = GAME_NAME, required = true) String gameName, @JsonProperty(value = DIFFICULTY, required = true) int difficulty) {
		this.playerIDstring = playerIDstring;
		this.gameName = gameName;
		this.difficulty = difficulty;
	}

	public PlayerID getPlayerID() {
		return new PlayerID(this.playerIDstring);
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
				+ "'" + PLAYER_ID + "'='" + this.playerIDstring + "'"
				+ "'" + GAME_NAME + "'='" + this.gameName + "'"
				+ "'" + DIFFICULTY + "'='" + this.difficulty + "'";
	}
}
