package org.prisching.tobias.Sudoku.messages.incoming;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;

import org.prisching.tobias.Sudoku.game.player.PlayerID;

public class GameCreationRequest extends Request {

	@NotNull
	@Size(min = 1)
	private String playerIDstring;
	
	@NotNull
	@Size(min = 1)
	private String gameName;
	
	@Min(0)
	@Max(81)
	private int difficulty;
	
	public GameCreationRequest(String playerIDstring, String gameName, int difficulty) {
		this.playerIDstring = playerIDstring;
		this.gameName = gameName;
		this.difficulty = difficulty;
	}
	
	public PlayerID getPlayerID() {
		return new PlayerID(this.playerIDstring);
	}
	
	public String getName() {
		return this.gameName;
	}
	
	public int getDifficulty() {
		return this.difficulty;
	}
}
