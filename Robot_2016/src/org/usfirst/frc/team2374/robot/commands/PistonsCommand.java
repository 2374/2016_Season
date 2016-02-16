package org.usfirst.frc.team2374.robot.commands;

import java.util.Arrays;
import java.util.List;

import org.usfirst.frc.team2374.robot.Command;
import org.usfirst.frc.team2374.robot.Component;
import org.usfirst.frc.team2374.robot.Robot;
import org.usfirst.frc.team2374.robot.events.Input;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class PistonsCommand extends Command {
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public List<Component> requires() {
        return Arrays.asList(Robot.pistons);
    }
    public void update (){
    	
    	Value frontLeft = Value.kReverse;
    	if (Input.getAxis(2) != 0) {
    		frontLeft = Value.kForward;
    	}
    	Value frontRight = Value.kReverse;
    	if (Input.getAxis(3) != 0) {
    		frontRight = Value.kForward;
    	}
    	Value backLeft = Value.kReverse;
    	if (Input.getButton(1)) {
    		backLeft = Value.kForward;
    	}
    	Value backRight = Value.kReverse;
    	if (Input.getButton(3)) {
    		backRight = Value.kForward;
    	}
    	
    	Robot.pistons.setPistons(frontLeft, frontRight, backLeft, backRight);
    	
    }

}
