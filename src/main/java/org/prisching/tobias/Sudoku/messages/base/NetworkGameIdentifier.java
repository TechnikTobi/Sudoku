package org.prisching.tobias.Sudoku.messages.base;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import org.prisching.tobias.Sudoku.messages.validation.IsAlpha;
import org.prisching.tobias.Sudoku.messages.validation.NotNullNorEmpty;

public final class NetworkGameIdentifier {
	
	private String identifier;

	public NetworkGameIdentifier(String identifier) {
		this.identifier = IsAlpha.check(NotNullNorEmpty.check(identifier, "Identifier should not be null nor empty"), null);
	}
	
	@JsonGetter(JSONnames.GAME_ID)
	public String getIdentifier() {
		return this.identifier;
	}

	@JsonSetter
	public void setIdentifier(String identifier) {
		this.identifier = IsAlpha.check(NotNullNorEmpty.check(identifier, "Identifier should not be null nor empty"), null);
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
