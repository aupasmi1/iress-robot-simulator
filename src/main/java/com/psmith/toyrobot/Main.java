package com.psmith.toyrobot;

import java.util.Scanner;

import com.psmith.toyrobot.exception.RobotException;
import com.psmith.toyrobot.state.Board;
import com.psmith.toyrobot.state.Robot;

public class Main {

	public static void main(String[] args) throws RobotException {

		System.out.println("Toy Robot Simulator");
		System.out.println("Enter a command, Valid commands are:");
		System.out.println("\'PLACE X,Y,NORTH|SOUTH|EAST|WEST\', MOVE, LEFT, RIGHT, REPORT or EXIT");

		Board board = new Board(4);
        Robot robot = new Robot();
        Simulator game = new Simulator(board, robot);
        
		try (Scanner scanner = new Scanner(System.in)) {
			boolean keepRunning = true;
			while (keepRunning) {
				String inputString = scanner.nextLine();
				if ("EXIT".equals(inputString)) {
					keepRunning = false;
				} else {
					try {
						System.out.println(game.eval(inputString));
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
			}
		}
		System.exit(0);
	}
}