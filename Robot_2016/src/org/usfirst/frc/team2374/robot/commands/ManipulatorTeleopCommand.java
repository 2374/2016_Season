package org.usfirst.frc.team2374.robot.commands;

import java.util.Arrays;
import java.util.List;

import org.usfirst.frc.team2374.robot.Command;
import org.usfirst.frc.team2374.robot.Component;
import org.usfirst.frc.team2374.robot.Robot;
import org.usfirst.frc.team2374.robot.events.Input;

public class ManipulatorTeleopCommand extends Command {

	public double manipulatorSpeed1;
	public double manipulatorSpeed2;

	@Override
	public boolean isFinished() {
		return false;
	}

	@Override
	public List<Component> requires() {
		return Arrays.asList(Robot.manipulator);
	}

	@Override
	public void update() {
		if (Robot.manipulator.LimitSwitchFront.get()) {
			manipulatorSpeed1 = 0;
		}
		if (!Robot.manipulator.LimitSwitchFront.get()) {
			manipulatorSpeed1 = 0.5;
		}
		// if (Robot.manipulator.LimitSwitchBack.get()){
		// manipulatorSpeed2 = 0;
		// }
		// if (!Robot.manipulator.LimitSwitchBack.get()){
		// manipulatorSpeed2 = -0.5;
		// }
		if (Input.getButton(2)) {
			Robot.manipulator.setSpeed(manipulatorSpeed1);
		}
		if (Input.getButton(4)) {
			Robot.manipulator.setSpeed(-0.5);
		}
	}
}
