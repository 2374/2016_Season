package org.usfirst.frc.team2374.robot.controllers;

import org.usfirst.frc.team2374.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousController extends RobotController {
	
    public AutonomousController(Robot robot) {
        super(robot);
        final SendableChooser autoChooser = new SendableChooser(); //Add defaults and options for rough terrain and various other obstacles
        autoChooser.addDefault("Rough Terrain", new RoughTerrainAuto());
    }
    
    /*
     * FOCUS ON ROUGH TERRAIN AND MOAT FOR AUTONOMOUS
     */
    
    
    private SendableChooser autoChooser = new SendableChooser(); //Add defaults and options for rough terrain and various other obstacles
    
    @Override
    protected void onStart() {
        //Move forward full speed
        myRobot.drivetrain.setSpeed(1, 1);
        //Wait 2 seconds, delay duration subject to change
        delay(2, () -> {
            //Stop moving
            myRobot.drivetrain.setSpeed(0, 0);
        });
        
    }

    @Override
    protected void onUpdate() {
    }

    @Override
    protected void onFinish() {
    }

    @Override
    protected boolean shouldFinish() {
        return myRobot.isDisabled() || !myRobot.isAutonomous();
    }

    //Old autonomous code
    /*
    public void worstCaseAutonomous() {
        myRobot.setSafetyEnabled(false);
        drivetrain.update(1, 1, false, false);
        Timer.delay(2.0);//delay duration subject to change
        drivetrain.update(0, 0, false, false);//move forward full speed 2 seconds
    }

    public void terrainAutonomous() {//make more terrain autonomous modes
        myRobot.setSafetyEnabled(false);
        gyro.initGyro();
        drivetrain.update(1, 1, true, false);
        Timer.delay(4.0);//delay duration subject to change
        if (gyro.getRate() <= 1 && gyro.getRate() >= -1) {//values subject to change in future
            drivetrain.update(1, 1, true, false);
            Timer.delay(2.0);//delay duration subject to change
        }
        drivetrain.update(0, 0, false, false);
    }

    public void terrainAndShootAutonomous() {
        myRobot.setSafetyEnabled(false);
        gyro.initGyro();
        drivetrain.update(1, 1, true, false);
        Timer.delay(4.0);//delay duration subject to change
        if (gyro.getRate() <= 1 && gyro.getRate() >= -1) {//values subject to change as well
            drivetrain.update(1, 1, true, false);
            Timer.delay(2.0);//delay duration subject to change
        }
        drivetrain.update(0, 0, false, false);
        angledShooter.update(1, true, false);
    }
     */
}
