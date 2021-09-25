package com.psmith.toyrobot;

import static com.psmith.toyrobot.state.Messages.*;

import com.psmith.toyrobot.command.RobotCommand;
import com.psmith.toyrobot.exception.InputException;
import com.psmith.toyrobot.exception.RobotException;
import com.psmith.toyrobot.parser.InputParser;
import com.psmith.toyrobot.parser.StringInputParser;
import com.psmith.toyrobot.state.Board;
import com.psmith.toyrobot.state.Position;
import com.psmith.toyrobot.state.Robot;

public class Simulator {

    Board board;
    Robot robot;
    InputParser<String, RobotCommand> inputParser = new StringInputParser();

    public Simulator(Board board, Robot robot) throws RobotException {
        if(board == null) {
        	throw new RobotException(NULL_BOARD_MSG);
        }
    	this.board = board;
    	if(robot == null) {
    		throw new RobotException(NULL_ROBOT_MSG);
    	}
        this.robot = robot;
    }

    /**
     * Places the robot on the squareBoard 
     * in position X,Y and facing NORTH, SOUTH, EAST or WEST
     *
     * @param position Robot position
     * @return true if placed successfully
     * @throws RobotException
     */
    public boolean placeRobot(Position position) throws RobotException {

        if (position == null)
            throw new RobotException(INVALID_POSITION_OBJ_MSG);

        if (position.getDirection() == null)
            throw new RobotException(INVALID_DIRECTION_VAL_MSG);

        if (!board.isValidPosition(position))
            return false;

        robot.setPosition(position);
        return true;
    }

    /**
     * Evaluates and executes a string command.
     *
     * @param inputString Input command string
     * @return string value of the command
     * @throws RobotException, InputException
     *
     */
    public String eval(String inputString) throws RobotException, InputException {
        RobotCommand robotCommand = inputParser.parseInput(inputString);

        String output;

        switch (robotCommand.getCommand()) {
            case PLACE:
                output = String.valueOf(placeRobot(robotCommand.getPosition()));
                break;
            case MOVE:
                if (robot.getPosition() == null || !board.isValidPosition(robot.getPosition().getNextPosition()))
                    output = String.valueOf(false);
                else
                    output = String.valueOf(robot.move());
                break;
            case LEFT:
                output = String.valueOf(robot.rotateLeft());
                break;
            case RIGHT:
                output = String.valueOf(robot.rotateRight());
                break;
            case REPORT:
                output = report();
                break;
            default:
                throw new RobotException(INVALID_CMD_MSG);
        }

        return output;
    }

    /**
     * Returns the X,Y and Direction of the robot
     */
    public String report() {
        if (robot.getPosition() == null || robot.getPosition().getDirection() == null)
            return null;

        return robot.toString();
    }
}
