package org.usfirst.frc.team2374.robot.commands;

import java.util.Arrays;
import java.util.List;

import org.usfirst.frc.team2374.robot.Command;
import org.usfirst.frc.team2374.robot.Component;
import org.usfirst.frc.team2374.robot.Robot;
import org.usfirst.frc.team2374.robot.events.Input;

public class PistonsTeleopCommand extends Command {

	private static final int[][] PISTON_MODES = new int[][] { { 0, 0, 0, 0 },
			{ 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
			{ 9, 10, 11, 12 } };

	private int mostRecentPushedButton;

	@Override
	public boolean isFinished() {
		return false;
	}

	@Override
	public List<Component> requires() {
		return Arrays.asList(Robot.pistons);
	}

	@Override
	public void update() {

		boolean frontLeft = Input.getAxis(2) != 0;
		boolean frontRight = Input.getAxis(3) != 0;
		boolean backLeft = Input.getButton(3);
		boolean backRight = Input.getButton(1);

		if (Input.JOYSTICK2.getRawButton(1)) {
			mostRecentPushedButton = 1;
		}
		if (Input.JOYSTICK2.getRawButton(2)) {
			mostRecentPushedButton = 2;
		}
		if (Input.JOYSTICK2.getRawButton(3)) {
			mostRecentPushedButton = 3;
		}
		if (Input.JOYSTICK2.getRawButton(4)) {
			mostRecentPushedButton = 4;
		}
		if (Input.JOYSTICK2.getRawButton(5)) {// this is the reset button,
												// change it later
			mostRecentPushedButton = 0;
		}

		if (!frontLeft && !frontRight && !backLeft && !backRight) {
			/*
			 * if (Input.JOYSTICK1.getPOV() == -1) { //
			 * Robot.pistons.setPistons(false, false, false, false); } else {
			 */
			int mode = PISTON_MODES[mostRecentPushedButton][Input.JOYSTICK1
					.getPOV() / 90];
			Robot.pistons.setPistonMode(mode);
		} else {
			Robot.pistons
					.setPistons(frontLeft, frontRight, backLeft, backRight);
		}
	}
}
