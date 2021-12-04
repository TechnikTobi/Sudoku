package org.prisching.tobias.Sudoku.game.player;

import java.util.UUID;

public class PlayerID {

	private String identifier;

	public PlayerID() {
		this.identifier = UUID.randomUUID().toString();
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
