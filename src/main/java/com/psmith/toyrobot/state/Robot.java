package com.psmith.toyrobot.state;

import com.psmith.toyrobot.exception.RobotException;

/**
 * Implementation class for the robot with position state
 *
 */
public class Robot {

    private Position position;

    public Robot() {
    }

    public Robot(Position newPosition) {
        position = newPosition;
    }

    public boolean setPosition(Position newPosition) {
        if (newPosition == null)
            return false;

        position = newPosition;
        return true;
    }

    /**
     * Moves the robot one step 
     * forward based on the current direction
     *
     * @return true if moved successfully
     */
    public boolean move() throws RobotException {
        if (position == null)
            return false;

        position = position.getNextPosition();
        return true;
    }

    public Position getPosition() {
        return this.position;
    }

    /**
     * Rotates the robot 90 degrees LEFT
     *
     * @return true if rotated successfully
     */
    public boolean rotateLeft() {
        if (position == null || position.direction == null)
            return false;

        position.direction = position.direction.leftDirection();
        return true;
    }

    /**
     * Rotate 90 degrees RIGHT
     *
     * @return true if rotated successfully
     */
    public boolean rotateRight() {
        if (position == null || position.direction == null)
            return false;

        position.direction = position.direction.rightDirection();
        return true;
    }

    public String toString() {
    	return new StringBuilder().append(getPosition().getX()).append(",").append(getPosition().getY()).append(",").append(getPosition().getDirection().toString()).toString();
    }
}
