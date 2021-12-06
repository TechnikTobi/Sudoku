package org.prisching.tobias.Sudoku.board.generator;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.prisching.tobias.Sudoku.board.Board;
import org.prisching.tobias.Sudoku.board.BoardInfoExtractor;
import org.prisching.tobias.Sudoku.board.Field;
import org.prisching.tobias.Sudoku.board.Position;
import org.prisching.tobias.Sudoku.board.solver.BoardSolver;
import org.prisching.tobias.Sudoku.board.validation.BoardValidator;

public class BoardGenerator {

	private static final int MIN_V = 1;
	private static final int MAX_V = 9;
	
	private static final int MIN_DIFFICULTY = 1;
	private static final int MAX_DIFFICULTY = 60;
	
	public static Board generateFullBoard() {	
		return addRandomNumber(new Board());
	}
	
	// Recursive function to fill a board
	private static Board addRandomNumber(Board board) {
		
		BoardInfoExtractor extractor = new BoardInfoExtractor(board);
		
		// If the board is invalid to begin with, return null
		if(BoardValidator.getValidator().validate(board) == false) return null;
		
		// If there is no empty field, return the board as we are done (and it is already validated!)
		if(extractor.isFull()) return board;
		
		for(int i = 0; i < MAX_V*MAX_V; i++) {
			
			int x = i % MAX_V;
			int y = i / MAX_V;
			Position pos = new Position(x, y); 
			
			// Search for an empty field
			if(board.getField(pos).getValue() != 0) continue;
			
			List<Integer> numbers = IntStream.range(1, 10).boxed().collect(Collectors.toList());
			Collections.shuffle(numbers);
			
			for(Integer value : numbers) {
			
				// Check if value would be a valid value for this field
				if(extractor.getRow(pos).stream().filter(f -> f.getValue() == value).findAny().isPresent()) continue;
				if(extractor.getColumn(pos).stream().filter(f -> f.getValue() == value).findAny().isPresent()) continue;
				if(extractor.getSquare(pos).stream().filter(f -> f.getValue() == value).findAny().isPresent()) continue;
				
				// If value is valid for this field, set it and make recursive call to fill the next field
				board.getField(pos).setValue(value);
				Board newBoard = BoardGenerator.addRandomNumber(board);
				
				// If something went wrong further down the line, reset board and continue with next value
				if(newBoard == null) {
					board.getField(pos).setValue(0);
					continue;
				}
				return newBoard;
			}
			
			// If non of the values did the trick, return null so another, previously set field gets a new number
			if(board.getField(pos).getValue() == 0) {
				return null;
			}
		}
		
		return null;
	}
	
	// Uses a fully filled board and generates one to solve
	// Difficulty tells us how many fields should be set to 0 
	public static Board generateFinalBoard(Board board, int difficulty) {
	
		// Clean input data to useful range
		difficulty = (difficulty < MIN_DIFFICULTY) ? MIN_DIFFICULTY : difficulty;
		difficulty = (difficulty > MAX_DIFFICULTY) ? MAX_DIFFICULTY : difficulty;
		
		// Create copies of the original board so it won't be changed
		Board workingBoard = new Board(board);
		Board checkpoint = new Board(board);
		
		Random random = new Random();
		
		// Remember all fields that already have been reset
		List<Position> resettedPositions = new ArrayList<Position>();
		
		for(int i = 0; i < difficulty; i++) {
			
			// If the board has only one solution, set a new checkpoint, otherwise revert to checkpoint
			if(BoardSolver.solve(workingBoard) == 1) {
				checkpoint = new Board(workingBoard);
			}else {
				workingBoard = new Board(checkpoint);
			}
			
			// Select a random position that hasn't been reset
			Position randomPosition = new Position(random.nextInt(0, Board.MAX_X), random.nextInt(0, Board.MAX_Y));
			while(resettedPositions.contains(randomPosition)) {
				randomPosition = new Position(random.nextInt(0, Board.MAX_X), random.nextInt(0, Board.MAX_Y));
			}
			 
			workingBoard.getField(randomPosition).setValue(0);
			resettedPositions.add(randomPosition);
		}
		
		System.out.println("Set " + resettedPositions.size() + " fields to 0 (Wish was " + difficulty + ")");
		return checkpoint;
		
	}
	
}
