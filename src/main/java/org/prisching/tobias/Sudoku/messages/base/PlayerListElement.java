package org.prisching.tobias.Sudoku.messages.base;

import org.prisching.tobias.Sudoku.game.player.Player;
import org.prisching.tobias.Sudoku.game.player.PlayerColor;

import com.fasterxml.jackson.annotation.JsonGetter;

public class PlayerListElement {
	
	private final Player player;
	private final int points;
	
	public PlayerListElement(Player player, int points) {
		this.player = player;
		this.points = points;
	}
	
	@JsonGetter(JSONnames.PLAYER_NAME)
	public String getName() {
		return this.player.getName();
	}
	
	@JsonGetter(JSONnames.POINTS)
	public int getPoints() {
		return this.points;
	}
	
	@JsonGetter(JSONnames.COLOR)
	public String getColor() {
		return this.player.getColor().getHexString();
	}
	
}