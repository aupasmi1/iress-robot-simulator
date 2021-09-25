package com.psmith.toyrobot.state;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.psmith.toyrobot.state.Board;
import com.psmith.toyrobot.state.Position;

public class BoardTest {
	
	private Board board;
	
	@BeforeEach
	public void init() {
		board = new Board(4);
	}

    @Test
    public void testIsValidPosition() throws Exception {
        
        Position pos = new Position(1, 1, null);
        assertTrue(board.isValidPosition(pos));
    }

    @Test
    public void testIsInValidPosition() throws Exception {
        
        Position pos = new Position(3, 7, null);
        assertFalse(board.isValidPosition(pos));

        pos = new Position(-1, -1, null);
        assertFalse(board.isValidPosition(pos));
    }
    
}
