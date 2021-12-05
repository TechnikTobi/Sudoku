package org.prisching.tobias.Sudoku.main;

import java.util.Collections;

import org.prisching.tobias.Sudoku.board.Board;
import org.prisching.tobias.Sudoku.board.BoardInfoExtractor;
import org.prisching.tobias.Sudoku.board.generator.BoardGenerator;
import org.prisching.tobias.Sudoku.game.GameController;
import org.prisching.tobias.Sudoku.game.player.PlayerID;
import org.prisching.tobias.Sudoku.messages.outgoing.GameState;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class SudokuApplication {

	private static final int DEFAULT_PORT = 666;
	
	public static void main(String[] args) {
		
		SpringApplication app = new SpringApplication(SudokuApplication.class);
		
		app.setDefaultProperties(Collections.singletonMap("server.port", DEFAULT_PORT));
		app.run(args);
		
		System.out.println("Sudoku!");
		
		try {
			System.out.println((new ObjectMapper()).writeValueAsString(
					new GameState(new GameController(new PlayerID(), "name", 45))
			));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		/*
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
		*/
		
		//BoardInfoExtractor extractor = new BoardInfoExtractor(BoardGenerator.generateRandomPlayfield());
		//extractor.print();
	}

}
