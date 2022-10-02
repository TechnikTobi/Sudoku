package org.prisching.tobias.Sudoku.messages.outgoing;

import java.util.List;
import java.util.ArrayList;

import org.prisching.tobias.Sudoku.game.GameController;
import org.prisching.tobias.Sudoku.game.player.Player;
import org.prisching.tobias.Sudoku.messages.base.GamesListElement;
import org.prisching.tobias.Sudoku.messages.base.JSONnames;
import org.prisching.tobias.Sudoku.messages.base.NetworkGameIdentifier;

import com.fasterxml.jackson.annotation.JsonGetter;

public class GamesListResponse extends Response {
	
	private final List<GamesListElement> list;
	
	public GamesListResponse(List<GameController> games, List<Player> players) {
		this.list = new ArrayList<GamesListElement>();
		
		for(GameController gameController : games) {
			if(gameController.isJoinable()) {
				Player master = players.stream().filter(p -> p.getID().equals(gameController.getMaster())).findFirst().orElse(null);
				String masterName = (master == null) ? "" : master.getName();
				this.list.add(new GamesListElement(new NetworkGameIdentifier(gameController.getGame().getGameID().getGameID()), gameController.getGame().getName(), masterName, gameController.getGame().getDifficulty(), gameController.countReadyPlayers(), gameController.countTotalPlayers()));
			}
		}
	}
	
	@JsonGetter(JSONnames.GAMES)
	public List<GamesListElement> getGames() {
		return this.list;
	}
	
}
