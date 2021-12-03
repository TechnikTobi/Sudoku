package org.prisching.tobias.Sudoku.board;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class BoardInfoExtractor {

	private Board board;
	
	public BoardInfoExtractor(Board board) {
		this.board = board;
	}
	
	public Position getMaxXPos() {
		try {
			return this.board.getAllFields()
				.keySet()
				.stream()
				//.max((p1, p2) -> {return (p1.getX() < p2.getX() ? -1 : (p1.getX() == p2.getX() ? 0 : 1));})
				.max((p1, p2) -> {return (
						p1.getX() < p2.getX() ? -1 : (p1.getX() == p2.getX() ? (
							p1.getY() < p2.getY() ? -1 : (p1.getY() == p2.getY() ? 0 : 1)
						) : 1)
				);})
				.get();
		}catch(NoSuchElementException e) {
			return new Position();
		}
	}
	
	public Position getMaxYPos() {
		try {
			return this.board.getAllFields()
				.keySet()
				.stream()
				.max((p1, p2) -> {return (
						p1.getY() < p2.getY() ? -1 : (p1.getY() == p2.getY() ? (
							p1.getX() < p2.getX() ? -1 : (p1.getX() == p2.getX() ? 0 : 1)
						) : 1)
				);})
				.get();
		}catch(NoSuchElementException e) {
			return new Position();
		}
	}
	
	public List<List<Field>> getRows() {
		List<List<Field>> rows = new ArrayList<List<Field>>();
		
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
	
	public List<List<Field>> getColumns() {
		List<List<Field>> columns = new ArrayList<List<Field>>();
		
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
	
	public List<List<Field>> getSquares() {
		List<List<Field>> squares = new ArrayList<List<Field>>();
		
		for(int i = 0; i < 3; i++) {
			int x = i;
			for(int j = 0; j < 3; j++) {
				int y = j;
				squares.add(this.board.getAllFields()
						.values()
					.stream()
					.filter(f -> f.getPos().getX() >= 3 * x)
					.filter(f -> f.getPos().getX() < 3 * (x+1))
					.filter(f -> f.getPos().getY() >= 3 * y)
					.filter(f -> f.getPos().getY() < 3 * (y+1))
					.collect(Collectors.toList())
				);
			}
		}
		
		return squares;
	}
	
	public List<Field> getRow(Position pos) {
		for(List<Field> row : this.getRows() ) {
			for(Field field : row) {
				if(field.getPos().equals(pos)) return row;
			}
		}
		return null;
	}
	
	public List<Field> getColumn(Position pos) {
		for(List<Field> column : this.getColumns() ) {
			for(Field field : column) {
				if(field.getPos().equals(pos)) return column;
			}
		}
		return null;
	}
	
	public List<Field> getSquare(Position pos) {
		for(List<Field> square : this.getSquares() ) {
			for(Field field : square) {
				if(field.getPos().equals(pos)) return square;
			}
		}
		return null;
	}
	
	public void print() {
		for(int y = 0; y < this.getMaxYPos().getY() + 1; y++) {
			String rowString = "";
			for(int x = 0; x < this.getMaxXPos().getX() + 1; x++) {
				if(this.board.getAllFields().containsKey(new Position(x, y))) {
					int fieldValue = this.board.getField(new Position(x, y)).getValue();
					rowString = rowString + (fieldValue == 0 ? " " : fieldValue) + ((x == 2 || x == 5) ? " ║ " : " ");
				}
			}
			System.out.println(rowString);
			if(y == 2 || y == 5) {
				System.out.println("══════╬═══════╬══════");
			}
		}
			
	}
	
}
