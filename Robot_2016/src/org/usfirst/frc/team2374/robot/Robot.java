package org.usfirst.frc.team2374.robot;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;

import org.usfirst.frc.team2374.robot.controllers.AutonomousController;
import org.usfirst.frc.team2374.robot.controllers.DisabledController;
import org.usfirst.frc.team2374.robot.controllers.TeleopController;
import org.usfirst.frc.team2374.robot.controllers.TestController;

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

    public Robot() {
        joystick1 = new Joystick(0);
        joystick2 = new Joystick(1); //Check Ports!!!!!!!!!!!
        angledShooter = new Shooter(4, 5, 5, 6); //PLEASE CHANGE THIS PORT IN THE FUTURE! DO IT!
        drivetrain = new Drivetrain();
        manipulator = new Manipulator(7);
        intake = new Intake(6);
        gyro = new AnalogGyro(12);
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
