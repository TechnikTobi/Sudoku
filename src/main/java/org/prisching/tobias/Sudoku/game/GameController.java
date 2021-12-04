package org.prisching.tobias.Sudoku.game;

import java.util.Map;
import java.util.HashMap;

import org.prisching.tobias.Sudoku.game.player.PlayerID;

public class GameController {

	private GameID gameID;
	private String name;
	private Map<PlayerID, Integer> points;
	private BoardManager boardManager;
	
	public GameController(String name, int difficulty) {
		this.gameID = new GameID();
		this.name = name;
		this.points = new HashMap<PlayerID, Integer>();
		this.boardManager = new BoardManager(difficulty);
	}
	
	public GameID getID() {
		return this.gameID;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Map<PlayerID, Integer> getPoints() {
		return this.points;
	}
	
	public void addPlayer(PlayerID id) {
		this.points.put(id, 0);
	}
	
}
