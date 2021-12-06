package org.prisching.tobias.Sudoku.messages.incoming.rules;

import java.util.List;
import java.util.ArrayList;

import org.prisching.tobias.Sudoku.game.GameControllerManager;
import org.prisching.tobias.Sudoku.game.player.PlayerManager;

import org.prisching.tobias.Sudoku.messages.incoming.*;

public class RequestRuleManager {

	private GameControllerManager gameControllerManager;
	private PlayerManager playerManager;
	
	private List<IRequestValidationRule> rules; 
	
	public RequestRuleManager(GameControllerManager gameControllerManager, PlayerManager playerManager) {
		this.gameControllerManager = gameControllerManager;
		this.playerManager = playerManager;
		
		this.rules = new ArrayList<IRequestValidationRule>();
		
		// this.rules.add(...);
	}
	
	public void checkAllRequestRules(Request request) {
		for(IRequestValidationRule rule : this.rules) {
			if(request instanceof PlayerRegistrationRequest) rule.validatePlayerRegistration(request);
		}
	}

}
