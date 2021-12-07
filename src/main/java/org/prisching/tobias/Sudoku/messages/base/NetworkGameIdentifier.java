package org.prisching.tobias.Sudoku.messages.base;

import org.prisching.tobias.Sudoku.messages.validation.IsAlpha;
import org.prisching.tobias.Sudoku.messages.validation.NotNullNorEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class NetworkGameIdentifier {
	
	private final String identifier;
	
	@JsonCreator
	//public NetworkGameIdentifier(@JsonProperty(value = JSONnames.GAME_ID, required = true) String identifier) {
	public NetworkGameIdentifier(String identifier) {
		this.identifier = IsAlpha.check(NotNullNorEmpty.check(identifier, "Identifier should not be null nor empty"), null);
	}
	
	@JsonGetter(JSONnames.GAME_ID)
	public String getIdentifier() {
		return this.identifier;
	}
	
	@Override
	public int hashCode() {
		return this.identifier.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof NetworkGameIdentifier)) {
			return false;
		}
		return (((NetworkGameIdentifier)o).hashCode() == this.hashCode());
	}
	
}
