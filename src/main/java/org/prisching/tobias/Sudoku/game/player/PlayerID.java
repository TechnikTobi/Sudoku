package org.prisching.tobias.Sudoku.game.player;

import java.util.UUID;

/**
 * The Player is used to uniquely identify a player within the application
 */
public class PlayerID {

	/** The identifier, stored as a string */
	private final String identifier;

	/**
	 * Constructor for creating a new PlayerID object from an existing identifier
	 * @param identifier The identifier to store in the new PlayerID as a string
	 */
	public PlayerID(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * Constructor for creating a new, random PlayerID
	 */
	public PlayerID() {
		this(UUID.randomUUID().toString());
	}

	/**
	 * Gets the identifier as a string
	 * @return The identifier string
	 */
	public String getPlayerID() {
		return this.identifier;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof PlayerID)) {
			return false;
		}
		return (o.hashCode() == this.hashCode());
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		return this.identifier.hashCode();
	}
	
}
