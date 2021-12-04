package org.prisching.tobias.Sudoku.game;

public class GameController {

	private GameID gameID;
	private String name;
	private int difficulty;
	
	public GameController(String name, int difficulty) {
		this.gameID = new GameID();
		this.name = name;
		this.difficulty = difficulty;
	}
	
	public GameID getID() {
		return this.gameID;
	}
	
	public String getName() {
		return this.name;
	}
	
}
