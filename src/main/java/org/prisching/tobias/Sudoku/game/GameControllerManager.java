package org.prisching.tobias.Sudoku.game;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.List;

import org.prisching.tobias.Sudoku.game.player.PlayerID;

public class GameControllerManager {

	private Map<GameID, GameController> games;
	
	public GameControllerManager() {
		this.games = new HashMap<GameID, GameController>();
	}
	
	public GameID createGame(PlayerID playerID, String name, int difficulty) {
		GameController newGame = new GameController(playerID, name, difficulty);
		this.games.put(newGame.getID(), newGame);
		return newGame.getID();
	}
	
	public GameController getGame(GameID id) {
		return this.games.get(id);
	}
	
	public List<GameController> getAllGames() {
		return this.games.values().stream().collect(Collectors.toList());
	}
	
}
