package org.prisching.tobias.Sudoku.messages.generator;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.prisching.tobias.Sudoku.game.GameController;
import org.prisching.tobias.Sudoku.game.player.PlayerID;
import org.prisching.tobias.Sudoku.game.player.PlayerManager;
import org.prisching.tobias.Sudoku.messages.base.NetworkField;
import org.prisching.tobias.Sudoku.messages.base.PlayerListElement;
import org.prisching.tobias.Sudoku.messages.outgoing.GameStateResponse;

public class GameStateResponseGenerator {

	public static GameStateResponse generate(GameController gameController, PlayerManager playerManager, String message) {
		List<NetworkField> fields = new ArrayList<NetworkField>();
		List<PlayerListElement> players = new ArrayList<PlayerListElement>();
		String newMessage = "The game has started!";
		if(gameController != null) {
			if(gameController.isJoinable()) {
				newMessage = "Waiting for all players to be ready (currently " + gameController.countReadyPlayers() + " out of " + gameController.countTotalPlayers() + " are ready)";
				fields = null;
			}
			if(!gameController.isJoinable()) {
				fields = gameController.getBoard().getAllFields()
					.values()
					.stream()
					.map(f -> new NetworkField(f.getPos().getX(), f.getPos().getY(), f.getValue(), f.getColor().getHexString()))
					.collect(Collectors.toList());
			}
			players = gameController.getPoints()
					.entrySet()
					.stream()
					.map(e -> new PlayerListElement(
							playerManager.getPlayer(e.getKey()).getName(),
							playerManager.getPlayer(e.getKey()).getColor().getHexString(),
							e.getValue().intValue())
					)
					.collect(Collectors.toList());
			if(gameController.isFinished()) {
				int max = 0;
				for(Integer points : gameController.getPoints().values().stream().collect(Collectors.toList())) {
					if(points.intValue() > max) max = points.intValue();
				}
				final int finalMax = max;
				newMessage = (gameController.getPoints().values().stream().filter(v -> v.intValue() == finalMax).count() > 1) ? "The winners are: " : "The winner is: ";
				
				boolean first = true;
				for(PlayerID playerID : gameController.getPoints().keySet().stream().collect(Collectors.toList())) {
					if(gameController.getPoints().get(playerID).intValue() == max) {
						newMessage = newMessage + (first ? "" : ", ") + playerManager.getPlayer(playerID).getName();
						first = false;
					}
				}
			}
		}
		
		//return (message == null) ? new GameStateResponse(fields, players, newMessage) : new GameStateResponse(fields, players, message);
		return new GameStateResponse(fields, players, newMessage);
	}
	
	public static GameStateResponse generate(GameController gameController, PlayerManager playerManager) {
		System.out.println("Why do I get called?");
		return generate(gameController, playerManager, null);
	}
}
