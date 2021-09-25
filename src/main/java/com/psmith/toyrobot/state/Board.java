package com.psmith.toyrobot.state;

public class Board {

    int rows;
    int columns;

    public Board(int size) {
        this.rows = size;
        this.columns = size;
    }

	public boolean isValidPosition(Position position) {
		return !(position.getX() > this.columns || 
				position.getX() < 0 || position.getY() > this.rows
				|| position.getY() < 0);
	}
}
