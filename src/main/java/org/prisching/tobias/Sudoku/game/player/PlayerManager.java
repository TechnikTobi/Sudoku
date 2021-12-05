package org.prisching.tobias.Sudoku.game.player;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;

public class PlayerManager {

	private Map<PlayerID, Player> players;
	
	public PlayerManager() {
		this.players = new HashMap<PlayerID, Player>();
	}
	
	public PlayerID addPlayer(String name) {
		Player newPlayer = new Player(name);
		this.players.put(newPlayer.getID(), newPlayer);
		return newPlayer.getID();
	}
	
	public Player getPlayer(PlayerID id) {
		return this.players.get(id);
	}
	
	public List<Player> getAllPlayers() {
		return this.players.values().stream().collect(Collectors.toList());
	}
}
