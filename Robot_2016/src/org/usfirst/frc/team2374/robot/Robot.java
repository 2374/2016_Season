
package org.usfirst.frc.team2374.robot;


import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.AnalogGyro;
/**
 * This is a demo program showing the use of the RobotDrive class.
 * The SampleRobot class is the base of a robot application that will automatically call your
 * Autonomous and OperatorControl methods at the right time as controlled by the switches on
 * the driver station or the field controls.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're inexperienced,
 * don't. Unless you know what you are doing, complex code will be much more difficult under
 * this system. Use IterativeRobot or Command-Based instead if you're new.
 */


public class Robot extends SampleRobot {
    RobotDrive myRobot;
    Joystick stick;
    Shooter angledShooter;
    ChainLift chainlift;
    Intake intake;
    Drivetrain drivetrain;
    ButtonControl buttonControl;
    AnalogGyro gyro;

    public Robot() {
        myRobot = new RobotDrive(0, 1);
        myRobot.setExpiration(0.1);
        stick = new Joystick(0);
        angledShooter=new Shooter(10, 11, 12); //PLEASE CHANGE THIS PORT IN THE FUTURE! DO IT!
        drivetrain = new Drivetrain();
        chainlift = new ChainLift(13);
        intake = new Intake(14);
        gyro = new AnalogGyro(12);
        buttonControl = new ButtonControl(angledShooter,chainlift,drivetrain,intake);
    }

    /**
     * Drive left & right motors for 2 seconds then stop
     */
    public void worstCaseAutonomous() {
        myRobot.setSafetyEnabled(false);
        drivetrain.update(1, 1, false, false);
        Timer.delay(2.0);
        drivetrain.update(0, 0, false, false);//move forward full speed 2 seconds
    }
    
    public void terrainAutonomous() {//make more terrain autonomous modes
    	myRobot.setSafetyEnabled(false);
    	gyro.initGyro();
    	drivetrain.update(1, 1, true, false);
        Timer.delay(4.0);
    	if(gyro.getRate()<=1 && gyro.getRate()>=-1) {
    		drivetrain.update(1, 1, true, false);
            Timer.delay(2.0);
    	}
    	drivetrain.update(0, 0, false, false);
    	}

    public void terrainAndShootAutonomous() {
    	myRobot.setSafetyEnabled(false);
    	gyro.initGyro();
        drivetrain.update(1, 1, true, false);
        Timer.delay(4.0);
    	if(gyro.getRate()<=1 && gyro.getRate()>=-1) {
    		drivetrain.update(1, 1, true, false);
            Timer.delay(2.0);
    	}
    	drivetrain.update(0, 0, false, false);
    	angledShooter.update(1, true, false);
    }
    /**
     * Runs the motors with arcade steering.
     */
    public void operatorControl() {
        myRobot.setSafetyEnabled(true);
        while (isOperatorControl() && isEnabled()) {
            buttonControl.update();
            Timer.delay(0.005);		// wait for a motor update time
        }
    }

    /**
     * Runs during test mode
     */
    public void test() {
    }
}
