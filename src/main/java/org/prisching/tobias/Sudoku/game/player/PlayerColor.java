package org.prisching.tobias.Sudoku.game.player;

import java.awt.Color;
import java.util.Random;

public class PlayerColor {

	private int red;
	private int green;
	private int blue;
	
	public PlayerColor() {
		final int R = (int)(Math.random()*256);
        final int G = (int)(Math.random()*256);
        final int B= (int)(Math.random()*256);
        Color color = new Color(R, G, B); //random color, but can be bright or dull

        //to get rainbow, pastel colors
        Random random = new Random();
        final float hue = random.nextFloat();
        final float saturation = 0.9f;//1.0 for brilliant, 0.0 for dull
        final float luminance = 1.0f; //1.0 for brighter, 0.0 for black
        color = Color.getHSBColor(hue, saturation, luminance);
        
        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();
	}
	
	public String getHexString() {
		return Integer.toHexString(this.red) + Integer.toHexString(this.green) + Integer.toHexString(this.blue);
	}
}
