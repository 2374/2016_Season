package org.usfirst.frc.team2374.robot.commands;

import java.util.Arrays;
import java.util.List;

import org.usfirst.frc.team2374.robot.Command;
import org.usfirst.frc.team2374.robot.Component;
import org.usfirst.frc.team2374.robot.Robot;

public class ManipulatorAutonomousCommand extends Command {

	public double manipulatorSpeed1;
	public double manipulatorSpeed2;
	public boolean directionIsDown;

	public ManipulatorAutonomousCommand(boolean d) {
		super();
		directionIsDown = d;
	}

	@Override
	public boolean isFinished() {
		return directionIsDown && manipulatorSpeed1 == 0 || !directionIsDown
				&& manipulatorSpeed2 == 0 || !Robot.robot.isAutonomous();
	}

	@Override
	public List<Component> requires() {
		return Arrays.asList(Robot.manipulator);
	}

	/**
	 * Sets direction based on an input boolean; false is up, true is down.
	 * 
	 * @param d
	 *            the desired direction of the manipulator- again, false is up,
	 *            true is down.
	 */
	public void setDirection(boolean d) {
		directionIsDown = d;
	}

	@Override
	public void update() {
		if (Robot.manipulator.LimitSwitchFront.get()) {
			manipulatorSpeed1 = 0;
		}
		if (!Robot.manipulator.LimitSwitchFront.get()) {
			manipulatorSpeed1 = 0.5;
		}
		// if (Robot.manipulator.LimitSwitchBack.get()) {
		manipulatorSpeed2 = 0;
		// }
		// if (!Robot.manipulator.LimitSwitchBack.get()) {
		manipulatorSpeed2 = -0.5;
		// }
		if (directionIsDown) {
			Robot.manipulator.setSpeed(manipulatorSpeed1);
		}
		if (!directionIsDown) {
			Robot.manipulator.setSpeed(manipulatorSpeed2);
		}

	}

}
