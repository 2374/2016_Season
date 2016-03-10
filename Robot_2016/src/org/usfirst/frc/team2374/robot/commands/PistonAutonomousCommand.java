package org.usfirst.frc.team2374.robot.commands;

import java.util.Arrays;
import java.util.List;

import org.usfirst.frc.team2374.robot.Command;
import org.usfirst.frc.team2374.robot.Component;
import org.usfirst.frc.team2374.robot.Robot;

public class PistonAutonomousCommand extends Command {

	public int AutocaseSelected;

	public PistonAutonomousCommand(int AutoCase) {
		AutocaseSelected = AutoCase;
	}

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		if(!Robot.robot.isAutonomous()){
			return true;
		}
		return false;
	}

	@Override
	public List<Component> requires() {
		// TODO Auto-generated method stub
		return Arrays.asList(Robot.pistons);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		Robot.pistons.setPistonMode(AutocaseSelected);

	}

}
