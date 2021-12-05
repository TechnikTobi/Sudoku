package org.prisching.tobias.Sudoku.game;

public enum EPlacementState {

	CORRECT {
		@Override public int points() {
			return 100;
		}
	},
	INCORRECT {
		@Override public int points() {
			return -100;
		}
	},
	INVALID {
		@Override public int points() {
			return 0;
		}
	};
	
	public abstract int points();
}
