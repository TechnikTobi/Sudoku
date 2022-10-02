package org.prisching.tobias.Sudoku.messages.base;

import com.fasterxml.jackson.annotation.JsonGetter;

/**
 * A network class for transmitting data of {@link org.prisching.tobias.Sudoku.game.GameController} objects
 */
public class GamesListElement {

	/** The GameID of the game as {@link NetworkGameIdentifier} object */
	private final NetworkGameIdentifier netGameID;

	/** The name of the game */
	private final String gameName;

	/** The name of the player who created the game */
	private final String creatorName;


	/** The difficulty of the game */
	private final int difficulty;

	private final int readyPlayers;
	private final int totalPlayers;

	public GamesListElement(NetworkGameIdentifier netGameID, String gameName, String masterName, int difficulty, int readyPlayers, int totalPlayers) {
		this.netGameID = netGameID;
		this.gameName = gameName;
		this.creatorName = masterName;
		
		this.difficulty = difficulty;
		this.readyPlayers = readyPlayers;
		this.totalPlayers = totalPlayers;
	}

	/**
	 * JSON-Getter for the GameID
	 * @return The GameID as string
	 */
	@JsonGetter(JSONnames.GAME_ID)
	public String getGameID() {
		return this.netGameID.getIdentifier();
	}

	/**
	 * JSON-Getter for the game's name
	 * @return The name as string
	 */
	@JsonGetter(JSONnames.GAME_NAME)
	public String getGameName() {
		return this.gameName;
	}

	/**
	 * JSON-Getter for the name of the game's creator
	 * @return The creator name as string
	 */
	@JsonGetter(JSONnames.CREATOR_NAME)
	public String getMasterName() {
		return this.creatorName;
	}

	/**
	 * JSON-Getter for the game's difficulty
	 * @return The difficulty as int
	 */
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