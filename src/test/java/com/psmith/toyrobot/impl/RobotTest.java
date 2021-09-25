package com.psmith.toyrobot.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.psmith.toyrobot.exception.RobotException;
import com.psmith.toyrobot.state.Direction;
import com.psmith.toyrobot.state.Position;
import com.psmith.toyrobot.state.Robot;

public class RobotTest {

	@Test
	public void testSetPosition() {
		Robot robot = new Robot(new Position(0, 0, Direction.NORTH));
		assertTrue(robot.setPosition(new Position(0, 0, Direction.EAST)));
		assertFalse(robot.setPosition(null));
	}
	
	@Test
	public void testRotate() {
		Robot robot = new Robot(new Position(0, 0, Direction.NORTH));
		assertTrue(robot.rotateLeft());
		assertTrue(robot.rotateRight());
		
		robot = new Robot(new Position(0, 0, null));
		assertFalse(robot.rotateLeft());
		assertFalse(robot.rotateRight());
	}
	
    @Test
    public void testMovement() throws RobotException {
    	Robot robot = new Robot();
    	assertFalse(robot.move());
    	
        robot = new Robot(new Position(0, 0, Direction.NORTH));

        assertTrue(robot.move());
        assertEquals(0, robot.getPosition().getX());
        assertEquals(1, robot.getPosition().getY());
        assertEquals(Direction.NORTH, robot.getPosition().getDirection());


        robot.setPosition(new Position(1, 2, Direction.EAST));
        robot.move();
        robot.move();
        robot.rotateLeft();
        robot.move();

        assertEquals(3, robot.getPosition().getX());
        assertEquals(3, robot.getPosition().getY());
        assertEquals(Direction.NORTH, robot.getPosition().getDirection());

        robot.setPosition(new Position(0, 0, Direction.NORTH));
        robot.rotateRight();
        assertEquals(Direction.EAST, robot.getPosition().getDirection());
        robot.rotateRight();
        assertEquals(Direction.SOUTH, robot.getPosition().getDirection());
        robot.rotateRight();
        assertEquals(Direction.WEST, robot.getPosition().getDirection());
        robot.rotateRight();
        assertEquals(Direction.NORTH, robot.getPosition().getDirection());
        robot.rotateRight();
        assertEquals(Direction.EAST, robot.getPosition().getDirection());
    }

}