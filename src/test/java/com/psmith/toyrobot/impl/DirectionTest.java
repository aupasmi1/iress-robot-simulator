package com.psmith.toyrobot.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.psmith.toyrobot.state.Direction;

public class DirectionTest {

    @Test
    public void testRotate() throws Exception {
        Direction direction = Direction.EAST;

        direction = direction.leftDirection();
        assertEquals(direction, Direction.NORTH);

        direction = direction.leftDirection();
        assertEquals(direction, Direction.WEST);

        direction = direction.leftDirection();
        assertEquals(direction, Direction.SOUTH);

        direction = direction.leftDirection();
        assertEquals(direction, Direction.EAST);

        direction = direction.leftDirection();
        assertEquals(direction, Direction.NORTH);

        direction = direction.rightDirection();
        assertEquals(direction, Direction.EAST);

        direction = direction.rightDirection();
        assertEquals(direction, Direction.SOUTH);

        direction = direction.rightDirection();
        assertEquals(direction, Direction.WEST);

        direction = direction.rightDirection();
        assertEquals(direction, Direction.NORTH);

        direction = direction.rightDirection();
        assertEquals(direction, Direction.EAST);
    }
}
