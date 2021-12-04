package org.prisching.tobias.Sudoku.main;

import java.util.Map;

import org.prisching.tobias.Sudoku.game.GameControllerManager;
import org.prisching.tobias.Sudoku.game.GameID;
import org.prisching.tobias.Sudoku.game.player.PlayerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value = "/app")
public class Endpoints {

	private PlayerManager playerManager;
	private GameControllerManager gameControllerManager;
	
	private static final String NAME = "name";
	private static final String DIFFICULTY = "difficulty";
	
	@Autowired
    private SimpMessagingTemplate messagingTemplate;
	
	public Endpoints() {
		this.playerManager = new PlayerManager();
		this.gameControllerManager = new GameControllerManager();
	}
	
	@PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
	private @ResponseBody String register(@RequestBody String data) {
		
		Map<String, Object> values = this.decodeJSON(data);
		
		if(values != null) {
			if(!values.containsKey(NAME)) return null;
			return encodeJSON(this.playerManager.addPlayer((String)values.get(NAME))); 
		}
		
		return null;
	}
	
	@PostMapping(value = "/createGame", consumes = "application/json", produces = "application/json")
	private @ResponseBody String createGame(@RequestBody String data) {
		
		Map<String, Object> values = this.decodeJSON(data);
		
		if(values != null) {
			if(!values.containsKey(NAME)) return null;
			if(!values.containsKey(DIFFICULTY)) return null;
			GameID newGameID = this.gameControllerManager.createGame((String)values.get(NAME), ((Integer) values.get(DIFFICULTY)).intValue());
			this.messagingTemplate.convertAndSend("/games", newGameID);
			return encodeJSON(newGameID); 
		}
		
		return null;
	}
	
	@MessageMapping("/game/{gameID}")
	private void processMove(@PathVariable GameID gameID) {
		
	}
	
	
	private String encodeJSON(Object data) {
		try {
			return (new ObjectMapper()).writeValueAsString(data);
		}catch(Exception e) {
			System.out.println("JSON encoding error: " + e.getMessage());
		}
		return null;
	}
	
	private Map<String, Object> decodeJSON(String jsonData) {
		try {
			return (new ObjectMapper()).readValue(jsonData, Map.class);
		}catch(Exception e) {
			System.out.println("JSON decoding error: " + e.getMessage());
		}
		return null;
	}
	
}
