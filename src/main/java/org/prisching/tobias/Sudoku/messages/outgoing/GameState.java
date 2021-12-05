package org.prisching.tobias.Sudoku.messages.outgoing;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonGetter;

import org.prisching.tobias.Sudoku.board.Field;
import org.prisching.tobias.Sudoku.game.GameController;

public class GameState extends Response {

	private GameController gameController;
	
	public GameState(GameController gameController) {
		this.gameController = gameController;
	}
	
	@JsonGetter("fields")
	public List<Field> getFields() {
		return this.gameController.getBoard().getAllFields().values().stream().collect(Collectors.toList());
	}
	
	@JsonGetter("players")
	public List<Integer> getPlayers() {
		return null;
	}
	
}
