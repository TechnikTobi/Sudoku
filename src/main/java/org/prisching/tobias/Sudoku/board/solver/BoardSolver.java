package org.prisching.tobias.Sudoku.board.solver;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.prisching.tobias.Sudoku.board.Board;
import org.prisching.tobias.Sudoku.board.BoardInfoExtractor;
import org.prisching.tobias.Sudoku.board.Position;
import org.prisching.tobias.Sudoku.board.generator.BoardGenerator;
import org.prisching.tobias.Sudoku.board.validation.BoardValidator;

public class BoardSolver {

	public static int solve(Board board) {
		return BoardSolver.recursiveSolve(board);
	}
	
	private static int recursiveSolve(Board board) {
	
		if(BoardValidator.getValidator().validate(board) == false) return 0;
		
		if(board.getAllFields().values().stream().filter(f -> f.getValue() == 0).findAny().isEmpty()) {
			return 1;
		}
		
		for(int i = 0; i < Board.MAX_X*Board.MAX_Y; i++) {
			
			int x = i % Board.MAX_X;
			int y = i / Board.MAX_Y;
			Position pos = new Position(x, y); 
			
			if(board.getField(pos).getValue() != 0) continue;
			
			int returnValue = 0;
			BoardInfoExtractor extractor = new BoardInfoExtractor(board);
			List<Integer> numbers = IntStream.range(1, 10).boxed().collect(Collectors.toList());
			Collections.sort(numbers);
			
			for(Integer value : numbers) {
				
				if(extractor.getRow(pos).stream().filter(f -> f.getValue() == value).findAny().isPresent()) continue;
				if(extractor.getColumn(pos).stream().filter(f -> f.getValue() == value).findAny().isPresent()) continue;
				if(extractor.getSquare(pos).stream().filter(f -> f.getValue() == value).findAny().isPresent()) continue;
				
				board.getField(pos).setValue(value);
				returnValue += BoardSolver.recursiveSolve(board);
				board.getField(pos).setValue(0);
				
			}
			
			return returnValue;
		}
		
		return 0;
		
	}
	
}
