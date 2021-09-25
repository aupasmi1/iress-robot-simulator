package com.psmith.toyrobot.exception;

/**
 * Exceptions thrown after input validation
 */
public class InputException extends Exception {

	private static final long serialVersionUID = 2L;

	public InputException(String string) {
        super(string);
    }

}
