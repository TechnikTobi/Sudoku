package org.prisching.tobias.Sudoku.main;

import org.prisching.tobias.Sudoku.board.Board;
import org.prisching.tobias.Sudoku.board.BoardInfoExtractor;
import org.prisching.tobias.Sudoku.board.generator.BoardGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SudokuApplication {

	public static void main(String[] args) {
		SpringApplication.run(SudokuApplication.class, args);
		System.out.println("Sudoku!");
		
		Board board = BoardGenerator.generateFullBoard();
		
		if(board != null) {
			System.out.println("Success!");
			BoardInfoExtractor extractor = new BoardInfoExtractor(board);
			Board finalBoard = BoardGenerator.generateFinalBoard(board, 40);
			BoardInfoExtractor finalExtractor = new BoardInfoExtractor(finalBoard);
			extractor.print();
			System.out.println(" ");
			finalExtractor.print();
		}else {
			System.out.println("._.");
		}
		
		//BoardInfoExtractor extractor = new BoardInfoExtractor(BoardGenerator.generateRandomPlayfield());
		//extractor.print();
	}

}
