package com.psmith.toyrobot.exception;

/**
 * 
 * Exception thrown after invalid command execution
 *
 */
public class RobotException extends Exception {

	private static final long serialVersionUID = 1L;

	public RobotException(String string) {
        super(string);
    }

}
