package org.prisching.tobias.Sudoku.game.player;

import java.util.UUID;

public class PlayerID {

	private final String identifier;

	public PlayerID(String identifier) {
		this.identifier = identifier;
	}
	
	public PlayerID() {
		this(UUID.randomUUID().toString());
	}

	public String getPlayerID() {
		return this.identifier;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof PlayerID)) {
			return false;
		}
		return (((PlayerID)o).hashCode() == this.hashCode());
	}
	
	@Override
	public int hashCode() {
		return this.identifier.hashCode();
	}
	
}
