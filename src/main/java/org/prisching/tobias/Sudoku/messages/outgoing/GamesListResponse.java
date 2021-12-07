package org.prisching.tobias.Sudoku.messages.outgoing;

import java.util.List;
import java.util.ArrayList;

import org.prisching.tobias.Sudoku.game.GameID;
import org.prisching.tobias.Sudoku.game.GameController;
import org.prisching.tobias.Sudoku.game.player.Player;
import org.prisching.tobias.Sudoku.game.player.PlayerID;
import org.prisching.tobias.Sudoku.messages.base.JSONnames;

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
		
		@JsonGetter(JSONnames.GAME_ID)
		public String getGameID() {
			return this.gameID.getGameID();
		}
		
		@JsonGetter(JSONnames.GAME_NAME)
		public String getGameName() {
			return this.gameName;
		}
		
		@JsonGetter(JSONnames.MASTER_ID)
		public String getMasterID() {
			return this.masterID.getPlayerID();
		}
		
		@JsonGetter(JSONnames.MASTER_NAME)
		public String getMasterName() {
			return this.masterName;
		}
		
	}
	
	private List<GamesListElement> list;
	
	public GamesListResponse(List<GameController> games, List<Player> players) {
		this.list = new ArrayList<GamesListElement>();
		
		for(GameController game : games) {
			if(game.isJoinable()) {
				String masterName = players.stream().filter(p -> p.getID().equals(game.getMaster())).findFirst().get().getName();
				this.list.add(new GamesListElement(game.getID(), game.getName(), game.getMaster(), masterName));
			}
		}
	}
	
	@JsonGetter(JSONnames.GAMES)
	public List<GamesListElement> getGames() {
		return this.list;
	}
	
}
