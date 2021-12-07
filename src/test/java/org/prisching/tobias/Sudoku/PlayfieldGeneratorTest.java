package org.prisching.tobias.Sudoku;

import org.junit.jupiter.api.*;
import org.prisching.tobias.Sudoku.board.generator.BoardGenerator;

public class PlayfieldGeneratorTest {

	@Test
	public void PlayfieldGenerationTest1() {
		System.out.println("Test success!");
		BoardGenerator.generateFullBoard();
	}
	
}
