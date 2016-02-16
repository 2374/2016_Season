package org.usfirst.frc.team2374.old_code.controllers;

import java.lang.reflect.Method;

import org.usfirst.frc.team2374.old_code.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousController extends RobotController {

	protected int autoCase;
	protected int turnDirection;
	private double xV, yV, zV, xP, yP, zP;
	private double turnSpeed, turnScale, turnMax, angleDifference;
	
	private double goal_x, goal_y;

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
		myRobot.gyro.calibrate();
		xV = 0;
		yV = 0;
		zV = 0;
		xP = 0;
		yP = 0;
		zP = 0;
		turnSpeed = 0;
		turnScale = 0.015;
		turnMax = 0.4;
		
		goal_x = 10;
		goal_y = 0;
		}
	private boolean atGoal(){
		if(xP==goal_x && yP==goal_y){
			return true;//when this is true update goal and desired angle values
		}
		else{
			return false;
		}
	}
	private boolean needsTurn(){
		if(yP!=goal_y && xP == goal_x){
		return true;
		}
		else{
			return false;
		}
	}
	//ADD THE TURN METHOD AND MOVE FORWARD METHOD ETC.

	@Override
	protected void onUpdate() {
		double xA = myRobot.accelerometer.getX() * 9.81 * Math.cos(myRobot.gyro.getAngle()*Math.PI/180);// Make sure the
														// accelerometer does
														// measure in g's
		double yA = myRobot.accelerometer.getY() * 9.81 * Math.sin(myRobot.gyro.getAngle()*Math.PI/180);//if axes change with robot, make sure to multiply by cos and sin.
		double zA = myRobot.accelerometer.getZ() * 9.81;
		xV += xA * deltaTime();
		yV += yA * deltaTime(); // Just in case you're wondering, you multiply by deltaTime()
						// because that's the time interval in seconds. This
						// ensures that the velocity and later position is in
						// meters.
		zV += zA * deltaTime();
		xP += xV * deltaTime();
		yP += yV * deltaTime();
		zP += zV * deltaTime(); // At this point xP, yP, zP are representative of the x,
						// y, and z coordinates of the robot's position with
						// respect to the origin (its starting position at
						// autonomous. It is in meters. You may need to check
						// these units and revise the method in the future.
		boolean isOverObstacle = zP > -.05 && zP < .05 && xP > 3;// Ensure that
																	// forward
																	// is the x
																	// axis and
																	// 3 is a
																	// good
																	// number
																	// for the
																	// distance
																	// from
																	// starting
																	// point to
																	// obstacle.
		switch (autoCase) {// Also ensure that -.05 to .05 is a good range for
							// the z disance.
		case 1: // ROUGH TERRAIN AUTONOMOUS
			myRobot.drivetrain.setSolenoids(0);
			myRobot.drivetrain.setSpeed(0.5, 0.5);
			if (!isOverObstacle) {
				myRobot.drivetrain.setSpeed(0.5, 0.5);
			} else {
				autoCase = 3;
			}
			break;
		case 2: // MOAT AUTONOMOUS
			myRobot.drivetrain.setSolenoids(2);// Change as necessary
			myRobot.drivetrain.setSpeed(1, 1);
			if (!isOverObstacle) {
				myRobot.drivetrain.setSpeed(1, 1);
			} else {
				autoCase = 3;
			}
			break;
		case 3: //Re-orient robot after crossing obstacle
			myRobot.drivetrain.setSolenoids(0);
			if(myRobot.gyro.getAngle()!=0){
			turn(0);
			}
			else{
			autoCase=4;
			}
			break;
		case 4: //VERIFY X AND Y COORDINATES AS WELL AS TURN DIRECTIONS
			if (turnDirection == 1) { //When goal is straight ahead
				ShooterAutonomous(0,0,0);//Measure the field and insert proper positions! (goal_x1, goal_x2, goal_y1)
		}
			if (turnDirection == 2) { //When goal is to the left
				ShooterAutonomous(0,0,0);
			}
			
			if (turnDirection == 3) { //When goal is to the right
				ShooterAutonomous(0,0,0);
			}
		}
	}
	public void ShooterAutonomous(double goal_x1, double goal_x2, double goal_y1){//We should be at the correct y position after one goal.
		goal_x=goal_x1;
		goal_y=goal_y1;
		if (atGoal()){
			if(myRobot.gyro.getAngle() != 0){
			turn(0);
			}
			goal_x=goal_x2;
			goal_y=goal_y1;
		}
		else {myRobot.drivetrain.setSpeed(1,1);}
		
		if (needsTurn() && yP < goal_y && myRobot.gyro.getAngle() != 90){
			turn(90);
		}
		else if (needsTurn() && yP > goal_y && myRobot.gyro.getAngle() != -90){
			turn(-90);
		}
		else {myRobot.drivetrain.setSpeed(1,1);}
		if (atGoal()){
			myRobot.drivetrain.setSpeed(0,0);
			myRobot.angledShooter.update(1,true,false);
		}
		else {myRobot.drivetrain.setSpeed(1,1);
		}
	}
	public void turn(double desiredAngle){
    	angleDifference = desiredAngle-myRobot.gyro.getAngle();
    	if (Math.abs(angleDifference)>2){
    		turnSpeed=angleDifference*turnScale;
    	}
    	if(Math.abs(turnSpeed)>turnMax){
    		turnSpeed=turnMax*Math.signum(turnSpeed);
    	}
    		myRobot.drivetrain.setSpeed(turnSpeed, -turnSpeed);
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
