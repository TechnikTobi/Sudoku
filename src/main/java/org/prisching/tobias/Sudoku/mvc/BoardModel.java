package org.prisching.tobias.Sudoku.mvc;

import java.beans.PropertyChangeSupport;

import org.prisching.tobias.Sudoku.board.Board;

public class BoardModel {

	private Board board;
	private final PropertyChangeSupport boardChanges;
	
	public BoardModel(Board board) {
		this.board = board;
		this.boardChanges = new PropertyChangeSupport(this);
	}
	
	public Board getBoard() {
		return this.board;
	}
	
}
