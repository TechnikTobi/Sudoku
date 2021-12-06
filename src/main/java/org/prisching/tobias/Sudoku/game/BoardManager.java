package org.prisching.tobias.Sudoku.game;

import org.prisching.tobias.Sudoku.board.Board;
import org.prisching.tobias.Sudoku.board.BoardInfoExtractor;
import org.prisching.tobias.Sudoku.board.Position;
import org.prisching.tobias.Sudoku.board.generator.BoardGenerator;
import org.prisching.tobias.Sudoku.game.player.PlayerColor;

public class BoardManager {

	private Board fullBoard;
	private Board playBoard;
	private BoardInfoExtractor playBoardExtractor;
	
	public BoardManager(int difficulty) {
		this.fullBoard = BoardGenerator.generateFullBoard();
		this.playBoard = BoardGenerator.generateFinalBoard(this.fullBoard, difficulty);
		this.playBoardExtractor = new BoardInfoExtractor(this.playBoard);
	}
	
	public EPlacementState setField(Position pos, int value, PlayerColor color) {
		if(this.playBoard.getAllFields().containsKey(pos)) {
			if(this.playBoard.getField(pos).getValue() == 0) {
				int correctValue = this.fullBoard.getAllFields().get(pos).getValue();
			
				if(correctValue == value) {
					this.playBoard.getField(pos).setValue(value);
					this.playBoard.getField(pos).setColor(color);
					
					return EPlacementState.CORRECT;
				}
				return EPlacementState.INCORRECT;
			}
		}
		return EPlacementState.INVALID;
	}
	
	public Board getPlayBoard() {
		return this.playBoard;
	}
	
	public boolean isPlayBoardFull() {
		return this.playBoardExtractor.isFull();
	}
	
}
