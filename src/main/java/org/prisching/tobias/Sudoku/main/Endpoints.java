package org.prisching.tobias.Sudoku.main;

import javax.servlet.http.HttpServletResponse;

import org.prisching.tobias.Sudoku.board.Field;
import org.prisching.tobias.Sudoku.board.Position;
import org.prisching.tobias.Sudoku.game.GameControllerManager;
import org.prisching.tobias.Sudoku.game.GameID;
import org.prisching.tobias.Sudoku.game.player.PlayerID;
import org.prisching.tobias.Sudoku.game.player.PlayerManager;
import org.prisching.tobias.Sudoku.messages.base.NetworkGameIdentifier;
import org.prisching.tobias.Sudoku.messages.base.NetworkPlayerIdentifier;
import org.prisching.tobias.Sudoku.messages.generator.GameStateResponseGenerator;
import org.prisching.tobias.Sudoku.messages.incoming.*;
import org.prisching.tobias.Sudoku.messages.outgoing.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;

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
	private @ResponseBody ResponseContainer register(@Validated @RequestBody PlayerRegistrationRequest request) {
		logger.info("Received request: " + request.getPrintString());
		Response data = new PlayerRegistrationResponse(new NetworkPlayerIdentifier(this.playerManager.addPlayer(request.getPlayerName()).getPlayerID()));
		return new ResponseContainer(data);
	}
	
	@PostMapping(value = "/createGame", consumes = APP_JSON, produces = APP_JSON)
	private @ResponseBody ResponseContainer createGame(@Validated @RequestBody GameCreationRequest request) {
		
		logger.info("Received request: " + request.getPrintString());
		
		PlayerID playerID = new PlayerID(request.getPlayerID().getIdentifier());
		
		// Should be replaced by actual validation
		if(this.playerManager.getPlayer(playerID) == null) {
			throw new RuntimeException("huhu");
		}
		
		this.gameControllerManager.createGame(playerID, request.getGameName(), request.getDifficulty());
		return generateGamesListAndSend();
	}
	
	@PostMapping(value = "/game/{gameID}/join", consumes = APP_JSON, produces = APP_JSON)
	private @ResponseBody ResponseContainer joinGame(@Validated @RequestBody GameJoinRequest request) {
		
		PlayerID playerID = new PlayerID(request.getNetPlayerID().getIdentifier());
		GameID gameID = new GameID(request.getNetGameID().getIdentifier());
		
		// Replace with real, proper validation
		if(this.playerManager.getPlayer(playerID) == null) {
			throw new RuntimeException("playerID not existant");
		}
		
		if(this.gameControllerManager.getGame(gameID) == null) {
			throw new RuntimeException("gameID not existant");
		}
		
		this.gameControllerManager.getGame(gameID).addPlayer(playerID);
		this.generateGameStateAndSend(gameID);
		return generateGamesListAndSend();
	}
	
	@PostMapping(value = "/game/{gameID}/ready", consumes = APP_JSON, produces = APP_JSON)
	private @ResponseBody ResponseContainer readyForGame(@Validated @RequestBody ReadyForGameRequest request) {
		
		PlayerID playerID = new PlayerID(request.getNetPlayerID().getIdentifier());
		GameID gameID = new GameID(request.getNetGameID().getIdentifier());
		
		// Replace with real, proper validation
		if(this.playerManager.getPlayer(playerID) == null) {
			throw new RuntimeException("playerID not existant");
		}
		
		if(this.gameControllerManager.getGame(gameID) == null) {
			throw new RuntimeException("gameID not existant");
		}
		
		if(!this.gameControllerManager.getGame(gameID).getPoints().containsKey(playerID)) {
			throw new RuntimeException("playerID does not belong to game with specified gameID");
		}
		
		this.gameControllerManager.getGame(gameID).readyPlayer(playerID);
		
		if(!this.gameControllerManager.getGame(gameID).isJoinable()) {
			generateGameStateAndSend(gameID);
		}
		
		this.generateGameStateAndSend(gameID);
		return generateGamesListAndSend();
	}
	
	@GetMapping(value = "/getGamesList", produces = APP_JSON)
	private @ResponseBody ResponseContainer getGamesList() {
		Response data = new GamesListResponse(this.gameControllerManager.getAllGames(), this.playerManager.getAllPlayers());
		return new ResponseContainer(data);
	}
	
	@MessageMapping(value = "/game/{gameID}/move")
	private void processMove(@DestinationVariable("gameID") NetworkGameIdentifier netGameID, MoveRequest request) {
		GameID gameID = new GameID(request.getNetGameID().getIdentifier());
		PlayerID playerID = new PlayerID(request.getNetPlayerID().getIdentifier());
		Field field = new Field(new Position(request.getNetField().getX(), request.getNetField().getY()), request.getNetField().getValue());
		this.gameControllerManager.getGame(gameID).setValue(this.playerManager.getPlayer(playerID), field);
		generateGameStateAndSend(gameID);
	}
	
	private ResponseContainer generateGamesListAndSend() {
		Response data = new GamesListResponse(this.gameControllerManager.getAllGames(), this.playerManager.getAllPlayers());
		ResponseContainer message = new ResponseContainer(data);
		this.messagingTemplate.convertAndSend("/gamesList", message);
		return message;
	}
	
	private ResponseContainer generateGameStateAndSend(GameID gameID) {
		GameStateResponse data = GameStateResponseGenerator.generate(this.gameControllerManager.getGame(gameID), this.playerManager, "test");
		ResponseContainer message = new ResponseContainer(data);
		this.messagingTemplate.convertAndSend("/game/" + gameID.getGameID() + "/update", message);
		return message;
	}
	
	@ExceptionHandler({ RuntimeException.class })
	public @ResponseBody String handleException(Exception exception, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		return exception.getMessage();
	}
}
