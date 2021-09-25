package com.psmith.toyrobot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.psmith.toyrobot.command.Command;
import com.psmith.toyrobot.exception.InputException;
import com.psmith.toyrobot.exception.RobotException;
import com.psmith.toyrobot.state.Board;
import com.psmith.toyrobot.state.Direction;
import com.psmith.toyrobot.state.Messages;
import com.psmith.toyrobot.state.Position;
import com.psmith.toyrobot.state.Robot;


public class SimulatorTest {
    static final int BOARD_DIMENSION = 5;
    
    @Test
    public void testInitGame() throws RobotException {
    	Board board = new Board(BOARD_DIMENSION);
        Robot toyRobot = new Robot();
        assertThrows(RobotException.class, () -> new Simulator(null, toyRobot));
        assertThrows(RobotException.class, () -> new Simulator(board, null));
    }
    
    @Test
    public void testPlacing() throws RobotException {

        Board board = new Board(BOARD_DIMENSION);
        Robot toyRobot = new Robot();
        Simulator game = new Simulator(board, toyRobot);

        assertTrue(game.placeRobot(new Position(0, 1, Direction.NORTH)));
        assertTrue(game.placeRobot(new Position(2, 2, Direction.SOUTH)));
        assertFalse(game.placeRobot(new Position(6, 6, Direction.WEST)));
        assertFalse(game.placeRobot(new Position(-1, 5, Direction.EAST)));
    }

    @Test
    public void testPlacingExceptions() throws RobotException {

        Board board = new Board(BOARD_DIMENSION);
        Robot toyRobot = new Robot();
        Simulator game = new Simulator(board, toyRobot);

        assertThrows(RobotException.class,() -> game.placeRobot(null));
        assertThrows(RobotException.class,() -> game.placeRobot(new Position(0, 1, null)));
    }

    @Test
    public void testEval() throws RobotException, InputException {

        Board board = new Board(BOARD_DIMENSION);
        Robot toyRobot = new Robot();
        Simulator simulator = new Simulator(board, toyRobot);
        
        //Invalid placement
        InputException ie = assertThrows(InputException.class, () -> simulator.eval("PLACE "));
        assertEquals("Invalid PLACE command", ie.getMessage());

        simulator.eval("PLACE 0,0,NORTH");
        assertEquals("0,0,NORTH", simulator.eval(Command.REPORT.name()));

        simulator.eval(Command.MOVE.name());
        simulator.eval(Command.RIGHT.name());
        simulator.eval(Command.MOVE.name());
        assertEquals("1,1,EAST", simulator.eval(Command.REPORT.name()));

        // if it goes out of the board it ignores the command
        for (int i = 0; i < 10; i++)
            simulator.eval(Command.MOVE.name());
        assertEquals("5,1,EAST", simulator.eval(Command.REPORT.name()));

        //rotate on itself
        for (int i = 0; i < 4; i++)
            simulator.eval(Command.LEFT.name());
        assertEquals("5,1,EAST", simulator.eval(Command.REPORT.name()));

        // invalid commands
        ie = assertThrows(InputException.class, () -> simulator.eval("XXXXXX"));
        assertEquals(Messages.INVALID_CMD_MSG, ie.getMessage());

    }
    
    @Test
    public void testInvalidRobotReport() throws RobotException {
    	Board board = new Board(BOARD_DIMENSION);
    	Robot robot = new Robot(null);
        Simulator simulator = new Simulator(board, robot);
        assertNull(simulator.report());
        robot = new Robot(new Position(0,0,null));
        assertNull(simulator.report());
    }
}
