package com.psmith.toyrobot.parser;

import com.psmith.toyrobot.exception.InputException;

public interface InputParser<I,O> {
	
	O parseInput(I input) throws InputException;

}
