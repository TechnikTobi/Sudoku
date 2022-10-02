package org.prisching.tobias.Sudoku.game;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.prisching.tobias.Sudoku.game.player.PlayerID;

public class GameControllerManager {

	private final Map<GameID, GameController> games;
	
	public GameControllerManager() {
		this.games = new HashMap<>();
	}
	
	public GameID createGame(PlayerID playerID, String name, int difficulty) {
		GameController gameController = new GameController(playerID, name, difficulty);
		this.games.put(gameController.getGame().getGameID(), gameController);
		return gameController.getGame().getGameID();
	}
	
	public GameController getGame(GameID id) {
		return this.games.get(id);
	}
	
	public List<GameController> getAllGames() {
		return new ArrayList<>(this.games.values());
	}
	
}
