package org.prisching.tobias.Sudoku.game.player;

/**
 * The Player class stores data relevant for a single player who can play multiple games
 */
public class Player {

	/** The identifier of the player */
	private final PlayerID playerID;

	/** The color that is associated with the player */
	private final PlayerColor color;

	/** The name of the player */
	private final String name;

	/**
	 * Constructor for creating a new Player object
	 * @param name The name of the new player
	 */
	public Player(String name) {
		this.playerID = new PlayerID();
		this.name = name;
		this.color = PlayerColor.newPlayerColor();
	}

	/**
	 * Gets the name of the player
	 * @return The player's name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the identifier of the player as {@link PlayerID}
	 * @return The player's identifier
	 */
	public PlayerID getID() {
		return this.playerID;
	}

	/**
	 * Gets the color of the player as {@link PlayerColor}
	 * @return The player's color
	 */
	public PlayerColor getColor() {
		return this.color;
	}
	
}
