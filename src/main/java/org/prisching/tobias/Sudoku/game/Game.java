package org.prisching.tobias.Sudoku.game;

/**
 * The game class stores most of the relevant data of a game
 */
public class Game {

    /** The identifier of the game as {@link GameID} object */
    private final GameID gameID;

    /** The name of the Game */
    private final String name;

    /** The difficulty value of the game */
    private final int difficulty;

    /**
     * Constructor for creating a new game object
     * @param name The name of the new game
     * @param difficulty The difficulty of the new game
     */
    public Game(String name, int difficulty) {
        this.gameID = new GameID();
        this.name = name;
        this.difficulty = difficulty;

    }

    /**
     * Gets the identifier of the game
     * @return The game's {@link GameID}
     */
    public GameID getGameID() {
        return this.gameID;
    }

    /**
     * Gets the name of the game
     * @return The game's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the game's difficulty the value
     * @return The game's difficulty
     */
    public int getDifficulty() {
        return this.difficulty;
    }
}
