package org.usfirst.frc.team2374.robot.commands;

import java.util.Arrays;
import java.util.List;

import org.usfirst.frc.team2374.robot.Command;
import org.usfirst.frc.team2374.robot.Component;
import org.usfirst.frc.team2374.robot.Robot;
import org.usfirst.frc.team2374.robot.events.Input;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;

public class PistonsTeleopCommand extends Command {
	
	public int[][] myArray = new int[][] {
			{0,0,0,0},
			{1,2,3,4},
			{5,6,7,8},
			{9,10,11,12},
			{9,10,11,12}
	};
	public int mostRecentPushedButton;
	
	
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
    	if (Input.JOYSTICK2.getRawButton(5)) {//this is the reset button, change it later
    		mostRecentPushedButton = 0;
    	}
    	
    	if (frontLeft == kReverse && frontRight == kReverse && backLeft == kReverse && backRight == kReverse){
    		
    		if (Input.JOYSTICK1.getPOV() == -1) {
    			Robot.pistons.setPistons(kReverse, kReverse, kReverse, kReverse);
    		} else {
    			int mode = myArray[mostRecentPushedButton][Input.JOYSTICK1.getPOV()/90];
    			Robot.pistons.setPistonMode(mode);
    			
    		}
    		
    	} else
    	Robot.pistons.setPistons(frontLeft, frontRight, backLeft, backRight);
    }

}
