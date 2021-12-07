package org.prisching.tobias.Sudoku.messages.base;

import org.prisching.tobias.Sudoku.messages.validation.NotNullNorEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class NetworkPlayerIdentifier {
	
	private final String identifier;
	
	@JsonCreator
	//public NetworkPlayerIdentifier(@JsonProperty(value = JSONnames.PLAYER_ID, required = true) String identifier) {
	public NetworkPlayerIdentifier(String identifier) {
		this.identifier = NotNullNorEmpty.check(identifier, "Identifier should not be null nor empty");
	}
	
	@JsonGetter(JSONnames.PLAYER_ID)
	public String getIdentifier() {
		return this.identifier;
	}
	
	@Override
	public int hashCode() {
		return this.identifier.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof NetworkPlayerIdentifier)) {
			return false;
		}
		return (((NetworkPlayerIdentifier)o).hashCode() == this.hashCode());
	}
}
