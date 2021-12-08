package org.prisching.tobias.Sudoku.messages.base;

import org.prisching.tobias.Sudoku.game.player.Player;
import org.prisching.tobias.Sudoku.game.player.PlayerColor;

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