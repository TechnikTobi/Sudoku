package org.prisching.tobias.Sudoku.game.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * The PlayerManager manages all players of the application
 */
public class PlayerManager {

	/** The map storing the data of all players */
	private final Map<PlayerID, Player> players;

	/**
	 * Constructor for creating a new PlayerManager
	 */
	public PlayerManager() {
		this.players = new HashMap<>();
	}

	/**
	 * Adds a player to the manager. Used when a player registers
	 * @param name The name of the new player
	 * @return The PlayerID of the new player
	 */
	public PlayerID addPlayer(String name) {
		Player newPlayer = new Player(name);
		this.players.put(newPlayer.getID(), newPlayer);
		return newPlayer.getID();
	}

	/**
	 * Gets the data of a player
	 * @param id The PlayerID of the player the data is wanted for
	 * @return The data of the player if found
	 */
	public Player getPlayer(PlayerID id) {
		return this.players.get(id);
	}

	/**
	 * Gets all players in the system
	 * @return A list of {@link Player} objects
	 */
	public List<Player> getAllPlayers() {
		return new ArrayList<>(this.players.values());
	}
}
