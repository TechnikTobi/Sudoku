package org.prisching.tobias.Sudoku.messages.base;

import com.fasterxml.jackson.annotation.JsonGetter;

public class GamesListElement {

	private final NetworkGameIdentifier netGameID;
	private final String gameName;
	private final String creatorName;
	
	private int difficulty;
	private int readyPlayers;
	private int totalPlayers;

	public GamesListElement(NetworkGameIdentifier netGameID, String gameName, String masterName, int difficulty, int readyPlayers, int totalPlayers) {
		this.netGameID = netGameID;
		this.gameName = gameName;
		this.creatorName = masterName;
		
		this.difficulty = difficulty;
		this.readyPlayers = readyPlayers;
		this.totalPlayers = totalPlayers;
	}
	
	@JsonGetter(JSONnames.GAME_ID)
	public String getGameID() {
		return this.netGameID.getIdentifier();
	}
	
	@JsonGetter(JSONnames.GAME_NAME)
	public String getGameName() {
		return this.gameName;
	}
	
	@JsonGetter(JSONnames.CREATOR_NAME)
	public String getMasterName() {
		return this.creatorName;
	}
	
	@JsonGetter(JSONnames.DIFFICULTY)
	public int getDifficulty() {
		return this.difficulty;
	}
	
	@JsonGetter(JSONnames.READY_PLAYERS)
	public int getReadyPlayers() {
		return this.readyPlayers;
	}
	
	@JsonGetter(JSONnames.TOTAL_PLAYERS)
	public int getTotalPlayers() {
		return this.totalPlayers;
	}
	
}