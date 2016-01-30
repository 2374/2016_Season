package org.usfirst.frc.team2374.robot.controllers;

import org.usfirst.frc.team2374.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousController extends RobotController {

	protected int autoCase;
	protected int turnDirection;
	private double xV, yV, zV, xP, yP, zP;

	public AutonomousController(Robot robot) {
		super(robot);
	}

	/*
	 * FOCUS ON ROUGH TERRAIN AND MOAT FOR AUTONOMOUS
	 */

	// private SendableChooser autoChooser = new SendableChooser(); //Add
	// defaults and options for rough terrain and various other obstacles

	@Override
	protected void onStart() {
		autoCase = (int) myRobot.autoChooser.getSelected();
		turnDirection = (int) myRobot.autoTurn.getSelected();
		boolean isOverObstacle = myRobot.gyro.getRate()<-1 || myRobot.gyro.getRate()>1; //These values should be changed!
		switch (autoCase) {
		case 1: // ROUGH TERRAIN AUTONOMOUS
			myRobot.drivetrain.setSolenoids(0);
			myRobot.drivetrain.setSpeed(.5, .5);
			delay(1, () -> {
				myRobot.drivetrain.setSpeed(.5, .5);
				if(!isOverObstacle){
					myRobot.drivetrain.setSpeed(.5, .5);
				}
				if(isOverObstacle){
					autoCase=3;	
			}
		});
			break;
		case 2: // MOAT AUTONOMOUS
			myRobot.drivetrain.setSolenoids(2);
			myRobot.drivetrain.setSpeed(1, 1);
			delay(0.5, () -> {
				if(!isOverObstacle){
					myRobot.drivetrain.setSpeed(1, 1);
				}
				if(isOverObstacle){
					autoCase=3;
			}
		});
			break;
		
			}
		}
	

	@Override
	protected void onUpdate() {
		double xA = myRobot.accelerometer.getX()*9.81;
		double yA = myRobot.accelerometer.getY()*9.81;
		double zA = myRobot.accelerometer.getZ()*9.81;
		xV += xA;
		yV += yA;
		zV += zA;
		xP += xV;
		yP += yV;
		zP += zV;
	if(autoCase==3){
		myRobot.drivetrain.setSolenoids(0);
		if(turnDirection==1){//Use the positions to maneuver to the goal
		}
		if(turnDirection==2){
		}
		if(turnDirection==3){
		}
		else{
			//SOMETHING SERIOUSLY GOT MESSED UP
		}
	}
	}

	@Override
	protected void onFinish() {
	}

	@Override
	protected boolean shouldFinish() {
		return myRobot.isDisabled() || !myRobot.isAutonomous();
	}

	// Old autonomous code
	/*
	 * public void worstCaseAutonomous() { myRobot.setSafetyEnabled(false);
	 * drivetrain.update(1, 1, false, false); Timer.delay(2.0);//delay duration
	 * subject to change drivetrain.update(0, 0, false, false);//move forward
	 * full speed 2 seconds }
	 * 
	 * public void terrainAutonomous() {//make more terrain autonomous modes
	 * myRobot.setSafetyEnabled(false); gyro.initGyro(); drivetrain.update(1, 1,
	 * true, false); Timer.delay(4.0);//delay duration subject to change if
	 * (gyro.getRate() <= 1 && gyro.getRate() >= -1) {//values subject to change
	 * in future drivetrain.update(1, 1, true, false); Timer.delay(2.0);//delay
	 * duration subject to change } drivetrain.update(0, 0, false, false); }
	 * 
	 * public void terrainAndShootAutonomous() {
	 * myRobot.setSafetyEnabled(false); gyro.initGyro(); drivetrain.update(1, 1,
	 * true, false); Timer.delay(4.0);//delay duration subject to change if
	 * (gyro.getRate() <= 1 && gyro.getRate() >= -1) {//values subject to change
	 * as well drivetrain.update(1, 1, true, false); Timer.delay(2.0);//delay
	 * duration subject to change } drivetrain.update(0, 0, false, false);
	 * angledShooter.update(1, true, false); }
	 */
}
