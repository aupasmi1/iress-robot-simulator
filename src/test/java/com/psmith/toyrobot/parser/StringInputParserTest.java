package com.psmith.toyrobot.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.psmith.toyrobot.command.Command;
import com.psmith.toyrobot.command.RobotCommand;
import com.psmith.toyrobot.exception.InputException;
import com.psmith.toyrobot.parser.InputParser;
import com.psmith.toyrobot.parser.StringInputParser;
import com.psmith.toyrobot.state.Direction;


public class StringInputParserTest {
    
	@Test
    public void testParseValidPlaceString() throws InputException {
    	InputParser<String, RobotCommand> parser = new StringInputParser();
    	RobotCommand robotCommand = parser.parseInput("PLACE 0,1,NORTH");
    	assertNotNull(robotCommand.getCommand());
    	assertNotNull(robotCommand.getPosition());
    	assertEquals(robotCommand.getCommand(), Command.PLACE);
    	assertEquals(robotCommand.getPosition().getDirection(), Direction.NORTH);
    	assertEquals(robotCommand.getPosition().getX(), 0);
    	assertEquals(robotCommand.getPosition().getY(), 1);
    }
    
	@Test
    public void testParseValidString() throws InputException {
    	InputParser<String, RobotCommand> parser = new StringInputParser();
    	for(Command c : Command.values()) {
    		if(c != Command.PLACE) {
    			RobotCommand robotCommand = parser.parseInput(c.name());
    	    	assertNotNull(robotCommand.getCommand());
    	    	assertNull(robotCommand.getPosition());
    	    	assertEquals(robotCommand.getCommand(), c);
    		}
    	}
    }
	

	@Test
    public void testParseInvalidPlaceString() throws InputException {
    	InputParser<String, RobotCommand> parser = new StringInputParser();
    	assertThrows(InputException.class, () -> parser.parseInput("PLACE "));
    	assertThrows(InputException.class, () -> parser.parseInput("XXX YYY ZZZ "));
    	assertThrows(InputException.class, () -> parser.parseInput("PLACE 0,Y,NORTH"));
    }

}
