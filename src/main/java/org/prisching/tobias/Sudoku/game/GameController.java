package org.prisching.tobias.Sudoku.game;

import java.util.Map;
import java.util.HashMap;

import org.prisching.tobias.Sudoku.board.Board;
import org.prisching.tobias.Sudoku.board.Position;
import org.prisching.tobias.Sudoku.game.player.Player;
import org.prisching.tobias.Sudoku.game.player.PlayerID;

public class GameController {

	private final GameID gameID;
	private String name;
	private final PlayerID master;
	private Map<PlayerID, Integer> points;
	private BoardManager boardManager;
	private EGameState gameState;
	
	public GameController(PlayerID master, String name, int difficulty) {
		this.gameID = new GameID();
		this.name = name;
		this.master = master;
		this.points = new HashMap<PlayerID, Integer>();
		this.points.put(master, 0);
		this.boardManager = new BoardManager(difficulty);
		this.gameState = EGameState.READY;
	}
	
	public GameID getID() {
		return this.gameID;
	}
	
	public String getName() {
		return this.name;
	}
	
	public PlayerID getMaster() {
		return this.master;
	}
	
	public Map<PlayerID, Integer> getPoints() {
		return this.points;
	}
	
	public Board getBoard() {
		return this.boardManager.getPlayBoard();
	}
	
	public void addPlayer(PlayerID id) {
		this.points.put(id, 0);
	}
	
	public void setValue(Player player, Position pos, int value) {
		if(this.points.containsKey(player.getID())) {
			this.points.put(player.getID(), this.points.get(player.getID()).intValue() + this.boardManager.setField(pos, value, player.getColor()).points());
		}
	}
}
