package com.psmith.toyrobot.parser;

import static com.psmith.toyrobot.state.Messages.INVALID_CMD_MSG;
import static com.psmith.toyrobot.state.Messages.PARAM_INVALID_CMD_MSG;

import com.psmith.toyrobot.command.Command;
import com.psmith.toyrobot.command.RobotCommand;
import com.psmith.toyrobot.exception.InputException;
import com.psmith.toyrobot.state.Direction;
import com.psmith.toyrobot.state.Position;

public class StringInputParser implements InputParser<String, RobotCommand> {

	@Override
	public RobotCommand parseInput(String input) throws InputException {
        String[] args = input.split(" ");
        final int validPlaceCommandLength = 2;
        
        Command command;
        try {
            command = Command.valueOf(args[0]);
        } catch (IllegalArgumentException e) {
            throw new InputException(INVALID_CMD_MSG);
        }
        if (command == Command.PLACE && args.length < validPlaceCommandLength) {
            throw new InputException(String.format(PARAM_INVALID_CMD_MSG, Command.PLACE));
        }

        RobotCommand robotCommand;
		if (command == Command.PLACE) {
			String[] params;
			int x,y = 0;
			Direction commandDirection = null;
			params = args[1].split(",");
			try {
				x = Integer.parseInt(params[0]);
				y = Integer.parseInt(params[1]);
				commandDirection = Direction.valueOf(params[2]);
			} catch (Exception e) {
				throw new InputException(INVALID_CMD_MSG);
			}
			robotCommand = new RobotCommand(command, new Position(x, y, commandDirection));
		} else {
			robotCommand = new RobotCommand(command, null);
		}
		return robotCommand;
	}

}
