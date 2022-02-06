package org.prisching.tobias.Sudoku.board;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The BoardInfoExtractor class helps to get more specific information out of a {@link Board} object
 */
public class BoardInfoExtractor {

	/** The side length of a square within the board */
	private final static int SQUARE_SIZE = 3;

	/** The {@link Board} object to extract information from */
	private final Board board;

	/**
	 * Constructor for creating a new BoardInfoExtractor
	 * @param board The board we want to extract information from
	 */
	public BoardInfoExtractor(Board board) {
		this.board = board;
	}

	/**
	 * Checks whether or not the board is full, that means, contains no field with the EMPTY_FIELD_VALUE value
	 * @return True if the board is full, false otherwise
	 */
	public boolean isFull() {
		return this.board.getAllFields().values().stream().filter(f -> f.getValue() == Field.EMPTY_FIELD_VALUE).findAny().isEmpty();
	}

	/**
	 * Gets the position of the field of the board with the maximum X coordinate
	 * @return The Position of said field
	 */
	public Position getMaxXPos() {
		return this.board.getAllFields()
			.keySet()
			.stream()
			.max((p1, p2) -> (
					p1.getX() < p2.getX() ? -1 : (p1.getX() == p2.getX() ? (
							Integer.compare(p1.getY(), p2.getY())
					) : 1)
			))
			.orElse(new Position());
	}

	/**
	 * Gets the position of the field of the board with the maximum Y coordinate
	 * @return The Position of said field
	 */
	public Position getMaxYPos() {
		return this.board.getAllFields()
			.keySet()
			.stream()
			.max((p1, p2) -> (
					p1.getY() < p2.getY() ? -1 : (p1.getY() == p2.getY() ? (
							Integer.compare(p1.getX(), p2.getX())
					) : 1)
			))
			.orElse(new Position());
	}

	/**
	 * Gets a list of all rows (which are list of {@link Field} objects) of the board
	 * @return A list of lists of fields, divided into the different rows
	 */
	public List<List<Field>> getRows() {
		List<List<Field>> rows = new ArrayList<>();
		
		for(int i = 0; i < Board.MAX_Y; i++) {
			int y = i; // due to enclosing scope we have to do this
			rows.add(this.board.getAllFields()
					.values()
					.stream()
					.filter(f -> f.getPos().getY() == y)
					.collect(Collectors.toList())
			);
		}
		
		return rows;
	}

	/**
	 * Gets a list of all columns (which are list of {@link Field} objects) of the board
	 * @return A list of lists of fields, divided into the different columns
	 */
	public List<List<Field>> getColumns() {
		List<List<Field>> columns = new ArrayList<>();
		
		for(int i = 0; i < Board.MAX_X; i++) {
			int x = i; // due to enclosing scope we have to do this
			columns.add(this.board.getAllFields()
					.values()
					.stream()
					.filter(f -> f.getPos().getX() == x)
					.collect(Collectors.toList())
			);
		}
		
		return columns;
	}

	/**
	 * Gets a list of all squares (which are list of {@link Field} objects) of the board
	 * @return A list of lists of fields, divided into the different squares
	 */
	public List<List<Field>> getSquares() {
		List<List<Field>> squares = new ArrayList<>();

		for(int i = 0; i < SQUARE_SIZE; i++) {
			int x = i;
			for(int j = 0; j < SQUARE_SIZE; j++) {
				int y = j;
				squares.add(this.board.getAllFields()
					.values()
					.stream()
					.filter(f -> f.getPos().getX() >= SQUARE_SIZE * x)
					.filter(f -> f.getPos().getX() < SQUARE_SIZE * (x+1))
					.filter(f -> f.getPos().getY() >= SQUARE_SIZE * y)
					.filter(f -> f.getPos().getY() < SQUARE_SIZE * (y+1))
					.collect(Collectors.toList())
				);
			}
		}
		
		return squares;
	}

	/**
	 * Gets a list of all {@link Field} objects within one of the 9 row of a Sudoku board
	 * @param pos The position of one of the fields within the desired row
	 * @return A list of the fields of the specified row
	 */
	public List<Field> getRow(Position pos) {
		for(List<Field> row : this.getRows() ) {
			for(Field field : row) {
				if(field.getPos().equals(pos)) return row;
			}
		}
		return null;
	}

	/**
	 * Gets a list of all {@link Field} objects within one of the 9 columns of a Sudoku board
	 * @param pos The position of one of the fields within the desired column
	 * @return A list of the fields of the specified column
	 */
	public List<Field> getColumn(Position pos) {
		for(List<Field> column : this.getColumns() ) {
			for(Field field : column) {
				if(field.getPos().equals(pos)) return column;
			}
		}
		return null;
	}

	/**
	 * Gets a list of all {@link Field} objects within one of the 9 squares of a Sudoku board
	 * @param pos The position of one of the fields within the desired square
	 * @return A list of the fields of the specified square
	 */
	public List<Field> getSquare(Position pos) {
		for(List<Field> square : this.getSquares() ) {
			for(Field field : square) {
				if(field.getPos().equals(pos)) return square;
			}
		}
		return null;
	}

	/**
	 * Method to print a board. Useful for debugging
	 */
	public void print() {
		for(int y = 0; y < this.getMaxYPos().getY() + 1; y++) {
			StringBuilder rowString = new StringBuilder();
			for(int x = 0; x < this.getMaxXPos().getX() + 1; x++) {
				if(this.board.getAllFields().containsKey(new Position(x, y))) {
					int fieldValue = this.board.getField(new Position(x, y)).getValue();
					rowString.append(fieldValue == 0 ? " " : fieldValue).append((x == (SQUARE_SIZE - 1) || x == (2 * SQUARE_SIZE - 1)) ? " ║ " : " ");
				}
			}
			System.out.println(rowString);
			if(y == (SQUARE_SIZE - 1) || y == (2*SQUARE_SIZE - 1)) System.out.println("══════╬═══════╬══════");
		}
	}
	
}
