package org.prisching.tobias.Sudoku.main;

import javax.servlet.http.HttpServletResponse;

import org.prisching.tobias.Sudoku.game.GameController;
import org.prisching.tobias.Sudoku.game.GameControllerManager;
import org.prisching.tobias.Sudoku.game.GameID;
import org.prisching.tobias.Sudoku.game.player.PlayerID;
import org.prisching.tobias.Sudoku.game.player.PlayerManager;
import org.prisching.tobias.Sudoku.messages.incoming.*;
import org.prisching.tobias.Sudoku.messages.outgoing.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/app")
public class Endpoints {

	private static final String APP_JSON = "application/json";
	
	private PlayerManager playerManager;
	private GameControllerManager gameControllerManager;
	private Logger logger;
	
	@Autowired
    private SimpMessagingTemplate messagingTemplate;
	
	public Endpoints() {
		this.playerManager = new PlayerManager();
		this.gameControllerManager = new GameControllerManager();
		this.logger = LoggerFactory.getLogger(Endpoints.class);
	}
	
	@PostMapping(value = "/register", consumes = APP_JSON, produces = APP_JSON)
	private @ResponseBody PlayerRegistrationResponse register(@Validated @RequestBody PlayerRegistrationRequest request) {
		logger.info("Received request: " + request.getPrintString());
		return new PlayerRegistrationResponse(this.playerManager.addPlayer(request.getPlayerName()));
	}
	
	@PostMapping(value = "/createGame", consumes = APP_JSON, produces = APP_JSON)
	private @ResponseBody GameCreationResponse createGame(@Validated @RequestBody GameCreationRequest request) {
		
		logger.info("Received request: " + request.getPrintString());
		
		PlayerID playerID = new PlayerID(request.getPlayerID().getIdentifier());
		
		if(this.playerManager.getPlayer(playerID) == null) {
			throw new RuntimeException("huhu");
		}
		
		GameID newGameID = this.gameControllerManager.createGame(playerID, request.getGameName(), request.getDifficulty());
		GameController newGame = this.gameControllerManager.getGame(newGameID);
		String newGameMasterName = this.playerManager.getPlayer(newGame.getMaster()).getName();
		GameCreationResponse message = new GameCreationResponse(newGameMasterName, newGame);
		
		this.messagingTemplate.convertAndSend("/games", message);
		return message;
	}
	
	@PostMapping(value = "/joinGame", consumes = APP_JSON, produces = APP_JSON)
	private @ResponseBody Response joinGame(@Validated @RequestBody Request request) {
		return null;
	}
	
	@GetMapping(value = "/getGamesList", produces = APP_JSON)
	private @ResponseBody Response getGamesList() {
		return new GamesListResponse(this.gameControllerManager.getAllGames(), this.playerManager.getAllPlayers());
	}
	
	@MessageMapping(value = "/game/{gameID}")
	private void processMove(@DestinationVariable("gameID") GameID gameID) {
		System.out.println("PROCESS MOVE FOR " + gameID.getGameID());
		this.gameControllerManager.getGame(gameID).setValue(null, null, 0);
		GameStateResponse message = new GameStateResponse(this.gameControllerManager.getGame(gameID), this.playerManager);
		this.messagingTemplate.convertAndSend("/game/" + gameID.getGameID(), message);
	}
	
	@ExceptionHandler({ RuntimeException.class })
	public @ResponseBody String handleException(Exception exception, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		return exception.getMessage();
	}
}
