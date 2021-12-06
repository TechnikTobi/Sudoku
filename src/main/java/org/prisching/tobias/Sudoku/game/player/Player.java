package org.prisching.tobias.Sudoku.game.player;

public class Player {

	private final PlayerID playerID;
	private PlayerColor color;
	private String name;
	
	public Player(String name) {
		this.playerID = new PlayerID();
		this.name = name;
		this.color = PlayerColor.newPlayerColor();
	}
	
	public String getName() {
		return this.name;
	}
	
	public PlayerID getID() {
		return this.playerID;
	}
	
	public PlayerColor getColor() {
		return this.color;
	}
	
}
