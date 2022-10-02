package org.prisching.tobias.Sudoku.game;

import java.util.Map;
import java.util.HashMap;

import org.prisching.tobias.Sudoku.board.Board;
import org.prisching.tobias.Sudoku.board.Field;
import org.prisching.tobias.Sudoku.board.Position;
import org.prisching.tobias.Sudoku.game.player.Player;
import org.prisching.tobias.Sudoku.game.player.PlayerID;

/**
 * The GameController controls a single game and handles it operations
 */
public class GameController {

	/** Number of points of an unready player */
	private final static int POINTS_UNREADY = -1;

	/** Number of points of a player who is ready */
	private final static int POINTS_READY = 0;

	/** The game to control */
	private final Game game;

	/** The identifier of the master of the game, i.e. who created the game */
	private final PlayerID master;

	private final Map<PlayerID, Integer> points;
	private final BoardManager boardManager;
	private EGameState gameState;
	
	public GameController(PlayerID master, String name, int difficulty) {
		this.game = new Game(name, difficulty);
		this.master = master;
		this.points = new HashMap<>();
		this.boardManager = new BoardManager(difficulty);
		this.gameState = EGameState.READY;
	}

	public Game getGame() {
		return this.game;
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
	
	public int countReadyPlayers() {
		return this.points.values().stream().filter(p -> p == POINTS_READY).toList().size();
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
	
	public void setFieldValue(Player player, Position pos, int value) {
		if(this.gameState.equals(EGameState.ONGOING)) {
			if(this.points.containsKey(player.getID())) {
				this.points.put(player.getID(), this.points.get(player.getID()) + this.boardManager.setField(pos, value, player.getColor()).points());
			}
			if(this.boardManager.isPlayBoardFull()) this.gameState = EGameState.FINISHED;
		}
	}
	
	public void setFieldValue(Player player, Field field) {
		this.setFieldValue(player, field.getPos(), field.getValue());
	}
}
