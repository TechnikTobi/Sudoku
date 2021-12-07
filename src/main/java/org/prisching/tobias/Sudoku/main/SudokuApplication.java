package org.prisching.tobias.Sudoku.main;

import java.util.Collections;

import org.prisching.tobias.Sudoku.game.GameController;
import org.prisching.tobias.Sudoku.game.player.PlayerID;
import org.prisching.tobias.Sudoku.game.player.PlayerManager;
import org.prisching.tobias.Sudoku.messages.outgoing.GameStateResponse;
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
			PlayerManager pm = new PlayerManager();
			PlayerID pid = pm.addPlayer("tobi");
			System.out.println((new ObjectMapper()).writeValueAsString(
					new GameStateResponse(new GameController(pid, "gameName", 45), pm)
			));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
