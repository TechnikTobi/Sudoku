package org.prisching.tobias.Sudoku.messages.generator;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.prisching.tobias.Sudoku.game.GameController;
import org.prisching.tobias.Sudoku.game.player.PlayerManager;
import org.prisching.tobias.Sudoku.messages.base.NetworkField;
import org.prisching.tobias.Sudoku.messages.base.PlayerListElement;
import org.prisching.tobias.Sudoku.messages.outgoing.GameStateResponse;

public class GameStateResponseGenerator {

	public static GameStateResponse generate(GameController gameController, PlayerManager playerManager, String message) {
		List<NetworkField> fields = new ArrayList<NetworkField>();
		if(gameController != null) {
			if(!gameController.isJoinable()) {
				fields = gameController.getBoard().getAllFields()
					.values()
					.stream()
					.map(f -> new NetworkField(f.getPos().getX(), f.getPos().getY(), f.getValue(), f.getColor().getHexString()))
					.collect(Collectors.toList());
			}
		}
		
		List<PlayerListElement> players = new ArrayList<PlayerListElement>();
		if(gameController != null) {
			players = gameController.getPoints()
				.entrySet()
				.stream()
				.map(e -> new PlayerListElement(playerManager.getPlayer(e.getKey()), e.getValue().intValue()))
				.collect(Collectors.toList());
		}
		
		return (message == null) ? new GameStateResponse(fields, players) : new GameStateResponse(fields, players, message);
	}
	
	public static GameStateResponse generate(GameController gameController, PlayerManager playerManager) {
		return generate(gameController, playerManager, null);
	}
}
