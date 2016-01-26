package org.usfirst.frc.team2374.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SampleRobot;
import org.usfirst.frc.team2374.robot.controllers.AutonomousController;
import org.usfirst.frc.team2374.robot.controllers.DisabledController;
import org.usfirst.frc.team2374.robot.controllers.TeleopController;
import org.usfirst.frc.team2374.robot.controllers.TestController;

public class Robot extends SampleRobot {

    public final Joystick joystick1;
    public final Joystick joystick2;
    public final Shooter angledShooter;
    public final ChainLift chainlift;
    public final Intake intake;
    public final Drivetrain drivetrain;
    //public final AnalogGyro gyro;

    public Robot() {
        joystick1 = new Joystick(0);
        joystick2 = new Joystick(1); //Check Ports!!!!!!!!!!!
        angledShooter = new Shooter(4, 5, 11, 12); //PLEASE CHANGE THIS PORT IN THE FUTURE! DO IT!
        drivetrain = new Drivetrain();
        chainlift = new ChainLift(6);
        intake = new Intake(7);
        //gyro = new AnalogGyro(12);
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
