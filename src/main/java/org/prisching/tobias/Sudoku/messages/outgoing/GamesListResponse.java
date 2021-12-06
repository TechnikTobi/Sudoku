package org.prisching.tobias.Sudoku.messages.outgoing;

import java.util.List;
import java.util.ArrayList;

import org.prisching.tobias.Sudoku.game.GameID;
import org.prisching.tobias.Sudoku.game.GameController;
import org.prisching.tobias.Sudoku.game.player.Player;
import org.prisching.tobias.Sudoku.game.player.PlayerID;

import com.fasterxml.jackson.annotation.JsonGetter;

public class GamesListResponse extends Response {

	public class GamesListElement {

		private GameID gameID;
		private String gameName;
		
		private PlayerID masterID;
		private String masterName;

		public GamesListElement(GameID gameID, String gameName, PlayerID masterID, String masterName) {
			this.gameID = gameID;
			this.gameName = gameName;
			this.masterID = masterID;
			this.masterName = masterName;
		}
		
		@JsonGetter("GameID")
		public String getGameID() {
			return this.gameID.getGameID();
		}
		
		@JsonGetter("GameName")
		public String getGameName() {
			return this.gameName;
		}
		
		@JsonGetter("MasterID")
		public String getMasterID() {
			return this.masterID.getPlayerID();
		}
		
		@JsonGetter("MasterName")
		public String getMasterName() {
			return this.masterName;
		}
		
	}
	
	private List<GamesListElement> list;
	
	public GamesListResponse(List<GameController> games, List<Player> players) {
		this.list = new ArrayList<GamesListElement>();
		
		for(GameController game : games) {
			String masterName = players.stream().filter(p -> p.getID().equals(game.getMaster())).findFirst().get().getName();
			this.list.add(new GamesListElement(game.getID(), game.getName(), game.getMaster(), masterName));
		}
	}
	
	@JsonGetter("Games")
	public List<GamesListElement> getGames() {
		return this.list;
	}
	
}
