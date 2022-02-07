package org.prisching.tobias.Sudoku.game.player;

import java.awt.Color;
import java.util.Random;

/**
 * The PlayerColor is a property of a player that can be used to make the game visually more appealing
 */
public class PlayerColor {

	/** The value of the red channel of the color */
	private final int red;

	/** The value of the green channel of the color */
	private final int green;

	/** The value of the blue channel of the color */
	private final int blue;

	/**
	 * Constructor for creating a new PlayerColor object with given RGB values
	 * @param red The value of the red channel of the color
	 * @param green The value of the green channel of the color
	 * @param blue The value of the blue channel of the color
	 */
	private PlayerColor(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	/**
	 * Gets the color as a string containing the hex values of the red, green and blue channels
	 * @return The color as a string of hex values in RGB order
	 */
	public String getHexString() {
		return Integer.toHexString(this.red) + Integer.toHexString(this.green) + Integer.toHexString(this.blue);
	}

	/**
	 * Creates a new, random color that can be directly used as player color
	 * @return The newly created color, ready to be used by a player
	 */
	public static PlayerColor newPlayerColor() {

        float hue = (new Random()).nextFloat();
        float saturation = 0.6f;	//1.0 for brilliant, 0.0 for dull
        float luminance = 0.8f; 	//1.0 for brighter, 0.0 for black
        Color color = Color.getHSBColor(hue, saturation, luminance);
		
		return new PlayerColor(color.getRed(), color.getGreen(), color.getBlue());
	}

	/**
	 * Gets the default color (black)
	 * @return The color black (0,0,0) as PlayerColor object
	 */
	public static PlayerColor getDefaultColor() {
		return new PlayerColor(0, 0, 0);
	}
}
