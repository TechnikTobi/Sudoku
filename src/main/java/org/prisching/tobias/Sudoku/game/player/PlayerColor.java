package org.prisching.tobias.Sudoku.game.player;

import java.awt.Color;
import java.util.Random;

public class PlayerColor {

	private int red;
	private int green;
	private int blue;
	
	private PlayerColor(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	public String getHexString() {
		return Integer.toHexString(this.red) + Integer.toHexString(this.green) + Integer.toHexString(this.blue);
	}
	
	public static PlayerColor newPlayerColor() {
		int R = (int)(Math.random()*256);
        int G = (int)(Math.random()*256);
        int B= (int)(Math.random()*256);
        Color color = new Color(R, G, B); //random color, but can be bright or dull

        //to get rainbow, pastel colors
        Random random = new Random();
        float hue = random.nextFloat();
        float saturation = 0.6f;//1.0 for brilliant, 0.0 for dull
        float luminance = 0.8f; //1.0 for brighter, 0.0 for black
        color = Color.getHSBColor(hue, saturation, luminance);
		
		return new PlayerColor(color.getRed(), color.getGreen(), color.getBlue());
	}
	
	public static PlayerColor getDefaultColor() {
		//return new PlayerColor(255, 255, 255);
		return new PlayerColor(0, 0, 0);
	}
}
