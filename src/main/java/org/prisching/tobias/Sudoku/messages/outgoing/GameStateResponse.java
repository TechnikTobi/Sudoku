package org.prisching.tobias.Sudoku.messages.outgoing;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonGetter;

import org.prisching.tobias.Sudoku.board.Field;
import org.prisching.tobias.Sudoku.game.GameController;
import org.prisching.tobias.Sudoku.game.player.Player;
import org.prisching.tobias.Sudoku.game.player.PlayerColor;
import org.prisching.tobias.Sudoku.game.player.PlayerManager;
import org.prisching.tobias.Sudoku.messages.base.JSONnames;
import org.prisching.tobias.Sudoku.messages.base.NetworkField;

public class GameStateResponse extends Response {

	private GameController gameController;
	private PlayerManager playerManager;
	
	public class PlayerListElement {
		
		private final Player player;
		private final int points;
		
		public PlayerListElement(Player player, int points) {
			this.player = player;
			this.points = points;
		}
		
		public String getName() {
			return this.player.getName();
		}
		
		public int getPoints() {
			return this.points;
		}
		
		public PlayerColor getColor() {
			return this.player.getColor();
		}
		
	}
	
	public GameStateResponse(GameController gameController, PlayerManager playerManager) {
		this.gameController = gameController;
		this.playerManager = playerManager;
	}
	
	public GameStateResponse(GameController gameController, PlayerManager playerManager, String message) {
		this(gameController, playerManager);
		this.setMessage(message);
	}
	
	@JsonGetter(JSONnames.FIELDS)
	public List<NetworkField> getFields() {
		if(this.gameController != null) {
			if(!this.gameController.isJoinable()) {
				return this.gameController.getBoard().getAllFields()
					.values()
					.stream()
					.map(f -> new NetworkField(f.getPos().getX(), f.getPos().getY(), f.getValue(), f.getColor().getHexString()))
					.collect(Collectors.toList());
			}
			return new ArrayList<NetworkField>();
		}
		return null;
	}
	
	@JsonGetter(JSONnames.PLAYERS)
	public List<PlayerListElement> getPlayers() {
		if(this.gameController != null) {
			return this.gameController.getPoints()
				.entrySet()
				.stream()
				.map(e -> new PlayerListElement(this.playerManager.getPlayer(e.getKey()), e.getValue().intValue()))
				.collect(Collectors.toList());
		}
		return null;
	}
	
}
