package org.prisching.tobias.Sudoku.game;

import java.sql.Timestamp;

/**
 * The GameID is used to uniquely identify a game within the application
 */
public class GameID {

	/** The identifier, stored as a long integer */
	private final long identifier;

	/**
	 * Constructor for creating a new GameID object
	 */
	public GameID() {
		this.identifier = new Timestamp(System.currentTimeMillis()).getTime();
	}

	/**
	 * Constructor for creating a GameID from a given identifier value
	 * @param identifierString The given identifier value as a string
	 */
	public GameID(String identifierString) {
		this.identifier = Long.parseLong(identifierString);
	}

	/**
	 * Gets the identifier value as a string
	 * @return The identifier value as string
	 */
	public String getGameID() {
		return String.valueOf(this.identifier);
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof GameID)) {
			return false;
		}
		return (o.hashCode() == this.hashCode());
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		return Long.valueOf(this.identifier).hashCode();
	}


}
