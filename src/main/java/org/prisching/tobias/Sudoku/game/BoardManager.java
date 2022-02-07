package org.prisching.tobias.Sudoku.game;

import org.prisching.tobias.Sudoku.board.Board;
import org.prisching.tobias.Sudoku.board.BoardInfoExtractor;
import org.prisching.tobias.Sudoku.board.Position;
import org.prisching.tobias.Sudoku.board.generator.BoardGenerator;
import org.prisching.tobias.Sudoku.game.player.PlayerColor;

/**
 * The BoardManager manages the two different boards needed to play a game
 */
public class BoardManager {

	/** The fully filled board to use as reference solution */
	private final Board fullBoard;

	/** The state of the board that gets shown to the players */
	private final Board playBoard;

	/** The information extractor to get infos about the playBoard */
	private final BoardInfoExtractor playBoardExtractor;

	/**
	 * Constructor for creating a new BoardManager
	 * @param difficulty The difficulty to use for creating the fullBoard
	 */
	public BoardManager(int difficulty) {
		this.fullBoard = BoardGenerator.generateFullBoard();
		this.playBoard = BoardGenerator.generateFinalBoard(this.fullBoard, difficulty);
		this.playBoardExtractor = new BoardInfoExtractor(this.playBoard);
	}

	/**
	 * Tries to set a field of the playBoard during the game
	 * @param pos The position of the field the player wants to set
	 * @param value The value the player wants to set the field to
	 * @param color The color of the player
	 * @return Returns the result of the operation as {@link EPlacementState} which tells whether or not the try was
	 * successful
	 */
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

	/**
	 * Gets the playBoard that gets shown to the players
	 * @return The playBoard object
	 */
	public Board getPlayBoard() {
		return this.playBoard;
	}

	/**
	 * Checks if the playBoard is full, i.e. not fields have the value EMPTY_FIELD_VALUE
	 * @return Whether or not the playBoard is full
	 */
	public boolean isPlayBoardFull() {
		return this.playBoardExtractor.isFull();
	}
	
}
