package org.usfirst.frc.team2374.robot.commands;

import java.util.Arrays;
import java.util.List;

import org.usfirst.frc.team2374.robot.Command;
import org.usfirst.frc.team2374.robot.Component;
import org.usfirst.frc.team2374.robot.Robot;
import org.usfirst.frc.team2374.robot.events.Input;

public class ShooterTeleopCommand extends Command{
	@Override
    public boolean isFinished() {
        return false;
	}
   public boolean FirstControllerActive(){
		 return (Input.JOYSTICK1.getRawButton(5)) || (Input.JOYSTICK1.getRawButton(6));
    }

	@Override
    public List<Component> requires() {
        return Arrays.asList(Robot.shooter);
    }
  
	@Override
 	public void update () {
	 if (!FirstControllerActive() && Input.JOYSTICK1.getRawButton(5)){
		 Robot.shooter.setSpeed(1);//implement PID
	 }
	 if (!FirstControllerActive() && Input.JOYSTICK1.getRawButton(6)){
		 Robot.shooter.setSpeed(-1);//implement PID
	 }
	 if (!FirstControllerActive() && Input.JOYSTICK2.getRawAxis(2)!=0){
		 Robot.shooter.setSpeed(1);//implement PID
	 }
	 if (!FirstControllerActive() && Input.JOYSTICK2.getRawAxis(3)!=0){
		 Robot.shooter.setSpeed(-1);//implement PID
	 }
	}
}