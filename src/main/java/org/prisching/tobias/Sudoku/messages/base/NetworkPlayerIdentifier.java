package org.prisching.tobias.Sudoku.messages.base;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import org.prisching.tobias.Sudoku.messages.validation.NotNullNorEmpty;

public final class NetworkPlayerIdentifier {

	private String identifier;

	public NetworkPlayerIdentifier(String identifier) {
		this.identifier = NotNullNorEmpty.check(identifier, "Identifier should not be null nor empty");
	}

	@JsonGetter(value = JSONnames.PLAYER_ID)
	public String getIdentifier() {
		return this.identifier;
	}

	@JsonSetter(value = JSONnames.PLAYER_ID)
	public void setIdentifier(String identifier) {
		this.identifier = NotNullNorEmpty.check(identifier, "Identifier should not be null nor empty");
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
