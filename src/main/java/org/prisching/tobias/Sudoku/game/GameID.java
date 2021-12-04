package org.prisching.tobias.Sudoku.game;

import java.sql.Timestamp;

public class GameID {
	
	private long identifier;
	
	public GameID(long identifier) {
		this.identifier = identifier;
	}
	
	public GameID() {
		this(new Timestamp(System.currentTimeMillis()).getTime());
	}
	
	public GameID(String identifierString) {
		this.identifier = Long.parseLong(identifierString);
	}
	
	public String getGameID() {
		return String.valueOf(this.identifier);
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof GameID)) {
			return false;
		}
		return (((GameID)o).hashCode() == this.hashCode());
	}
	
	@Override
	public int hashCode() {
		return Long.valueOf(this.identifier).hashCode();
	}


}
