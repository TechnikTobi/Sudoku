package org.prisching.tobias.Sudoku.main;

import java.util.Map;

import javax.validation.Valid;

import org.prisching.tobias.Sudoku.game.GameController;
import org.prisching.tobias.Sudoku.game.GameControllerManager;
import org.prisching.tobias.Sudoku.game.GameID;
import org.prisching.tobias.Sudoku.game.player.PlayerManager;
import org.prisching.tobias.Sudoku.messages.*;
import org.prisching.tobias.Sudoku.messages.incoming.*;
import org.prisching.tobias.Sudoku.messages.outgoing.GameCreationResponse;
import org.prisching.tobias.Sudoku.messages.outgoing.PlayerRegistrationResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;

import org.springframework.stereotype.Controller;

import org.springframework.validation.annotation.Validated;

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
	private @ResponseBody PlayerRegistrationResponse register(@Validated @RequestBody PlayerRegistrationRequest request) {
		return new PlayerRegistrationResponse(this.playerManager.addPlayer(request.getName()));
	}
	
	@PostMapping(value = "/createGame", consumes = "application/json", produces = "application/json")
	private @ResponseBody GameCreationResponse createGame(@Validated @RequestBody GameCreationRequest request) {
		
		/*
		Map<String, Object> values = this.decodeJSON(data);
		
		if(values != null) {
			if(!values.containsKey(NAME)) return null;
			if(!values.containsKey(DIFFICULTY)) return null;
			GameCreation message = new GameCreation(null, this.gameControllerManager.createGame((String)values.get(NAME), ((Integer) values.get(DIFFICULTY)).intValue()));
			this.messagingTemplate.convertAndSend("/games", message);
			//return encodeJSON(newGameID);
			return message;
		}
		
		return null;
		*/
		
		GameID newGameID = this.gameControllerManager.createGame(request.getPlayerID(), request.getName(), request.getDifficulty());
		GameController newGame = this.gameControllerManager.getGame(newGameID);
		String newGameMasterName = this.playerManager.getPlayer(newGame.getMaster()).getName();
		
		GameCreationResponse message = new GameCreationResponse(newGameMasterName, newGame);
		this.messagingTemplate.convertAndSend("/games", message);
		return message;
	}
	
	@MessageMapping(value = "/game/{gameID}")
	private void processMove(@DestinationVariable("gameID") GameID gameID) {
		System.out.println("PROCESS MOVE FOR " + gameID.getGameID());
		this.gameControllerManager.getGame(gameID).setValue(null, null, 0);
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
