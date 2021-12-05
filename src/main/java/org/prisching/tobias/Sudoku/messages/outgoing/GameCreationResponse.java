package org.prisching.tobias.Sudoku.messages.outgoing;

import org.prisching.tobias.Sudoku.game.player.PlayerID;

import com.fasterxml.jackson.annotation.JsonGetter;

import org.prisching.tobias.Sudoku.game.GameController;
import org.prisching.tobias.Sudoku.game.GameID;

public class GameCreationResponse extends PlayerRegistrationResponse {

	private GameID gameID;
	private String gameName;
	private String gameMaster; 

	public GameCreationResponse(String gameMaster, GameController gameController) {
		super(gameController.getMaster());
		this.gameID = gameController.getID();
		this.gameName = gameController.getName();
		this.gameMaster = gameMaster;
	}
	
	@JsonGetter("GameID")
	public String getGameIDstring() {
		return this.gameID.getGameID();
	}
	
	@JsonGetter("GameName")
	public String getGameName() {
		return this.gameName;
	}
	
	@JsonGetter("GameMaster")
	public String getGameMaster() {
		return this.gameMaster;
	}
	
}
