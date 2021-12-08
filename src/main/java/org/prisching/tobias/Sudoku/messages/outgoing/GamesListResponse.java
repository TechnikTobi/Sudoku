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
	
	private List<GamesListElement> list;
	
	public GamesListResponse(List<GameController> games, List<Player> players) {
		this.list = new ArrayList<GamesListElement>();
		
		for(GameController game : games) {
			if(game.isJoinable()) {
				String masterName = players.stream().filter(p -> p.getID().equals(game.getMaster())).findFirst().get().getName();
				this.list.add(new GamesListElement(new NetworkGameIdentifier(game.getID().getGameID()), game.getName(), masterName, game.getDifficulty(), game.countReadyPlayers(), game.countTotalPlayers()));
			}
		}
	}
	
	@JsonGetter(JSONnames.GAMES)
	public List<GamesListElement> getGames() {
		return this.list;
	}
	
}
