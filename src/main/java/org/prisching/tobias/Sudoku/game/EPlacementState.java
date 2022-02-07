package org.prisching.tobias.Sudoku.game;

/**
 * This enum describes the result of trying to set the value of a field by a player and the points they get for that
 */
public enum EPlacementState {

	CORRECT {
		/** {@inheritDoc} */
		@Override public int points() {
			return 100;
		}
	},
	INCORRECT {
		/** {@inheritDoc} */
		@Override public int points() {
			return -100;
		}
	},
	INVALID {
		/** {@inheritDoc} */
		@Override public int points() {
			return 0;
		}
	};

	/**
	 * Gets the amount of points the player receives for their action
	 * @return Number of points
	 */
	public abstract int points();
}
