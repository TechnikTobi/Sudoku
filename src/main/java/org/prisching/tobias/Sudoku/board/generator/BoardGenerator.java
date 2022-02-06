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

/**
 * The BoardGenerator class provides static methods for creating a new Board that gets filled randomly with numbers
 * so that it is a valid Sudoku board. The class also provides a method for removing values from a board to get a
 * Sudoku that can be used for playing games and still only has one solution.
 */
public class BoardGenerator {

	private static final int MIN_V = 1;
	private static final int MAX_V = 9;

	private static final int MIN_DIFFICULTY = 1;
	private static final int MAX_DIFFICULTY = 60;

	/**
	 * Generate a new valid Sudoku Board that is fully filled with numbers
	 * @return A Sudoku board fully filled with values so that the board is valid
	 */
	public static Board generateFullBoard() {	
		return addRandomNumber(new Board());
	}

	/**
	 * Recursive function for filling a Sudoku Board
	 * @param board The board to fill
	 * @return If filling the board was successful, the board gets returned. If it already is full, then the given board
	 * gets returned. If the given board is invalid or filling in a new number was not successful, null gets returned
	 */
	private static Board addRandomNumber(Board board) {
		
		BoardInfoExtractor extractor = new BoardInfoExtractor(board);
		
		// If the board is invalid to begin with, return null
		if(!BoardValidator.validate(board)) return null;
		
		// If there is no empty field, return the board as we are done (and it is already validated!)
		if(extractor.isFull()) return board;
		
		for(int i = 0; i < MAX_V*MAX_V; i++) {
			
			int x = i % MAX_V;
			int y = i / MAX_V;
			Position pos = new Position(x, y); 
			
			// Search for an empty field
			if(board.getField(pos).getValue() != Field.EMPTY_FIELD_VALUE) continue;
			
			List<Integer> numbers = IntStream.range(MIN_V, MAX_V+1).boxed().collect(Collectors.toList());
			Collections.shuffle(numbers);
			
			for(Integer value : numbers) {
			
				// Check if value would be a valid value for this field
				if(extractor.getRow(pos).stream().anyMatch(f -> f.getValue() == value)) continue;
				if(extractor.getColumn(pos).stream().anyMatch(f -> f.getValue() == value)) continue;
				if(extractor.getSquare(pos).stream().anyMatch(f -> f.getValue() == value)) continue;
				
				// If value is valid for this field, set it and make recursive call to fill the next field
				board.getField(pos).setValue(value);
				Board newBoard = BoardGenerator.addRandomNumber(board);
				
				// If something went wrong further down the line, reset board and continue with next value
				if(newBoard == null) {
					board.getField(pos).setValue(Field.EMPTY_FIELD_VALUE);
					continue;
				}
				return newBoard;
			}
			
			// If none of the values did the trick, return null so another, previously set field gets a new number
			if(board.getField(pos).getValue() == Field.EMPTY_FIELD_VALUE) {
				return null;
			}
		}

		return null;
	}

	/**
	 * Uses a fully filled board to generate a Sudoku with a given difficulty and one unique solution. The difficulty
	 * in this case means how many fields are set to 0, though the function can't guarantee to reach the desired
	 * difficulty.
	 * @param board The board to generate the Sudoku from
	 * @param difficulty The desired difficulty for the Sudoku
	 * @return The Sudoku ready to be used for playing a game
	 */
	public static Board generateFinalBoard(Board board, int difficulty) {
	
		// Clean input data to useful range
		difficulty = Math.max(difficulty, MIN_DIFFICULTY);
		difficulty = Math.min(difficulty, MAX_DIFFICULTY);
		
		// Create copies of the original board, so it won't be changed
		Board workingBoard = new Board(board);
		Board checkpoint = new Board(board);
		
		Random random = new Random();
		
		// Remember all fields that already have been cleared
		List<Position> clearedPositions = new ArrayList<>();
		
		for(int i = 0; i < difficulty; i++) {
			
			// If the board has only one solution, set a new checkpoint, otherwise revert to checkpoint
			if(BoardSolver.solve(workingBoard) == 1) {
				checkpoint = new Board(workingBoard);
			}else {
				workingBoard = new Board(checkpoint);
			}
			
			// Select a random position that hasn't been reset
			Position randomPosition = new Position(random.nextInt(Board.MAX_X), random.nextInt(Board.MAX_Y));
			while(clearedPositions.contains(randomPosition)) {
				randomPosition = new Position(random.nextInt(Board.MAX_X), random.nextInt(Board.MAX_Y));
			}
			 
			workingBoard.getField(randomPosition).setValue(Field.EMPTY_FIELD_VALUE);
			clearedPositions.add(randomPosition);
		}
		
		System.out.println("Set " + clearedPositions.size() + " fields to 0 (Wish was " + difficulty + ")");
		return checkpoint;
		
	}
	
}
