package org.usfirst.frc.team2374.old_code;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;

import org.usfirst.frc.team2374.old_code.controllers.AutonomousController;
import org.usfirst.frc.team2374.old_code.controllers.DisabledController;
import org.usfirst.frc.team2374.old_code.controllers.RobotController;
import org.usfirst.frc.team2374.old_code.controllers.TeleopController;
import org.usfirst.frc.team2374.old_code.controllers.TestController;

public class Robot extends SampleRobot {

    public final Joystick joystick1;
    public final Joystick joystick2;
    public final Shooter angledShooter;
    public final Manipulator manipulator;
    public final Intake intake;
    public final Drivetrain drivetrain;
    public final SendableChooser autoChooser;
    public final SendableChooser autoTurn;
    public final AnalogGyro gyro;
    public final BuiltInAccelerometer accelerometer;
    public final AnalogInput analogInputGyro;
    public RobotController robotController;

    public Robot() {
        joystick1 = new Joystick(0);
        joystick2 = new Joystick(1); //Check Ports!!!!!!!!!!!
        angledShooter = new Shooter(4, 5, 5, 6, this); //PLEASE CHANGE THIS PORT IN THE FUTURE! DO IT!
        drivetrain = new Drivetrain();
        manipulator = new Manipulator(7);
        intake = new Intake(6);
        analogInputGyro=new AnalogInput(0);
        gyro = new AnalogGyro(analogInputGyro);
        accelerometer = new BuiltInAccelerometer();
        autoChooser = new SendableChooser(); //Add defaults and options for rough terrain and various other obstacles
        autoTurn = new SendableChooser();
        autoChooser.addDefault("Rough Terrain", 1);
        autoChooser.addObject("Moat", 2);
        autoChooser.addObject("Shooter", 3);
        autoTurn.addDefault("Goal is straight ahead", 1);
        autoTurn.addObject("Goal is to the Left", 2);
        autoTurn.addObject("Goal is to the Right", 3);
        SmartDashboard.putNumber("Left Encoder Speed",drivetrain.getLeftRate());
        SmartDashboard.putNumber("Right Encoder Speed", drivetrain.getRightRate());
        SmartDashboard.putNumber("Wheel Encoder Speed",angledShooter.getRate());
        SmartDashboard.putNumber("X Acceleration", accelerometer.getX());
        SmartDashboard.putNumber("Y Acceleration", accelerometer.getY());
        SmartDashboard.putNumber("Z Acceleration", accelerometer.getZ());
        SmartDashboard.putData("Autonomous Mode Chooser", autoChooser);
        SmartDashboard.putData("Turn to find goal", autoTurn);
        
        
    }
    
    @Override
    public void autonomous() {
        new AutonomousController(this).run();
    }

    @Override
    public void disabled() {
        new DisabledController(this).run();
    }

    @Override
    public void operatorControl() {
        new TeleopController(this).run();
    }

    @Override
    public void test() {
        new TestController(this).run();
    }
}