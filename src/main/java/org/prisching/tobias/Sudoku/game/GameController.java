package org.prisching.tobias.Sudoku.game;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;

import org.prisching.tobias.Sudoku.board.Board;
import org.prisching.tobias.Sudoku.board.Field;
import org.prisching.tobias.Sudoku.board.Position;
import org.prisching.tobias.Sudoku.game.player.Player;
import org.prisching.tobias.Sudoku.game.player.PlayerID;

public class GameController {

	private final static int POINTS_UNREADY = -1;
	private final static int POINTS_READY = 0;
	
	private final GameID gameID;
	private final String name;
	private final PlayerID master;
	private final int difficulty;
	
	private Map<PlayerID, Integer> points;
	private BoardManager boardManager;
	private EGameState gameState;
	
	public GameController(PlayerID master, String name, int difficulty) {
		this.gameID = new GameID();
		this.name = name;
		this.master = master;
		this.difficulty = difficulty;
		this.points = new HashMap<PlayerID, Integer>();
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
	
	public int getDifficulty() {
		return this.difficulty;
	}
	
	public Map<PlayerID, Integer> getPoints() {
		return this.points;
	}
	
	public Board getBoard() {
		return this.boardManager.getPlayBoard();
	}
	
	public int countReadyPlayers() {
		return this.points.values().stream().filter(p -> p == POINTS_READY).collect(Collectors.toList()).size();
	}
	
	public int countTotalPlayers() {
		return this.points.size();
	}
	
	public boolean isJoinable() {
		return this.gameState.equals(EGameState.READY);
	}
	
	public boolean isFinished() {
		return this.gameState.equals(EGameState.FINISHED);
	}
	
	public boolean addPlayer(PlayerID id) {
		if(this.gameState.equals(EGameState.READY)) this.points.put(id, POINTS_UNREADY);
		return this.points.containsKey(id);
	}
	
	public void readyPlayer(PlayerID id) {
		if(this.points.containsKey(id)) {
			this.points.put(id, POINTS_READY);
		}
		if(this.countReadyPlayers() == this.countTotalPlayers()) {
			this.gameState = EGameState.ONGOING;
		}
	}
	
	public void setValue(Player player, Position pos, int value) {
		if(this.gameState.equals(EGameState.ONGOING)) {
			if(this.points.containsKey(player.getID())) {
				this.points.put(player.getID(), this.points.get(player.getID()).intValue() + this.boardManager.setField(pos, value, player.getColor()).points());
			}
			if(this.boardManager.isPlayBoardFull()) this.gameState = EGameState.FINISHED;
		}
	}
	
	public void setValue(Player player, Field field) {
		this.setValue(player, field.getPos(), field.getValue());
	}
}
