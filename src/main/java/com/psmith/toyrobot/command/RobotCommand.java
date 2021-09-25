package com.psmith.toyrobot.command;

import com.psmith.toyrobot.state.Position;

public class RobotCommand {
	private final Command command;
	private final Position position;
	
	public RobotCommand(Command command, Position position) {
		super();
		this.command = command;
		this.position = position;
	}

	public Command getCommand() {
		return command;
	}

	public Position getPosition() {
		return position;
	}
	
	
}
