package org.usfirst.frc.team2374.robot.commands;

import java.util.Arrays;
import java.util.List;

import org.usfirst.frc.team2374.robot.Command;
import org.usfirst.frc.team2374.robot.Component;
import org.usfirst.frc.team2374.robot.Robot;
import org.usfirst.frc.team2374.robot.events.Input;


public class PistonAutonomousCommand extends Command{
	
public int AutocaseSelected;

	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setCase(int autoCase){
		AutocaseSelected = autoCase;
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
