package com.psmith.toyrobot.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.psmith.toyrobot.exception.RobotException;
import com.psmith.toyrobot.state.Direction;
import com.psmith.toyrobot.state.Messages;
import com.psmith.toyrobot.state.Position;

public class PositionTest {

    @Test
    public void testGetNextPosition() throws Exception {
    	RobotException re = assertThrows(RobotException.class, () -> new Position(0, 0, null).getNextPosition());
    	assertEquals(Messages.INVALID_ROBOT_DIRECTION_MSG, re.getMessage());
        
    	Position position = new Position(0, 0, Direction.EAST);

        Position newPosition = position.getNextPosition();
        assertEquals(newPosition.getX(), 1);
        assertEquals(newPosition.getY(), 0);
        assertEquals(newPosition.getDirection(), Direction.EAST);

        newPosition = newPosition.getNextPosition();
        assertNotEquals(newPosition.getX(), 1);
        assertEquals(newPosition.getY(), 0);
        assertEquals(newPosition.getDirection(), Direction.EAST);

        newPosition.setDirection(Direction.NORTH);
        newPosition = newPosition.getNextPosition();
        assertNotEquals(newPosition.getX(), 1);
        assertEquals(newPosition.getY(), 1);
        assertNotEquals(newPosition.getDirection(), Direction.EAST);
        
        newPosition.setDirection(Direction.SOUTH);
        newPosition = newPosition.getNextPosition();
        assertNotEquals(newPosition.getX(), 1);
        assertEquals(newPosition.getY(), 0);
        assertNotEquals(newPosition.getDirection(), Direction.EAST);

        newPosition.setDirection(Direction.WEST);
        newPosition = newPosition.getNextPosition();
        assertEquals(newPosition.getX(), 1);
        assertEquals(newPosition.getY(), 0);
        assertNotEquals(newPosition.getDirection(), Direction.EAST);
    }
}
