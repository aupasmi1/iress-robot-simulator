package com.psmith.toyrobot.state;

import static com.psmith.toyrobot.state.Messages.*;

import com.psmith.toyrobot.exception.RobotException;

/**
 * 
 * Captures the position and orientation state
 *
 */
public class Position {
    private int x;
    private int y;
    Direction direction;

    public Position(Position position) {
        this.x = position.getX();
        this.y = position.getY();
        this.direction = position.getDirection();
    }

    public Position(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void change(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public Position getNextPosition() throws RobotException {
        if (this.direction == null)
            throw new RobotException(INVALID_ROBOT_DIRECTION_MSG);

        // new position to validate
        Position newPosition = new Position(this);
        switch (this.direction) {
            
        case NORTH:
                newPosition.change(0, 1);
                break;
            case EAST:
                newPosition.change(1, 0);
                break;
            case SOUTH:
                newPosition.change(0, -1);
                break;
            case WEST:
                newPosition.change(-1, 0);
                break;
        }
        return newPosition;
    }
}
