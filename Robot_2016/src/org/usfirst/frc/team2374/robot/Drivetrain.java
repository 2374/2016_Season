package org.usfirst.frc.team2374.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

public class Drivetrain {

    private final Talon l1, l2, r1, r2;// talons, pretty normal
    private final Encoder encoder;
    private final DoubleSolenoid solenoidBack, solenoidFront; // two solenoids, in the future
    // specify or modify these names based on the placement on the robot, for specification's sake

    public Drivetrain() {
        l1 = new Talon(0);// ports, to be changed later
        l2 = new Talon(1);
        r1 = new Talon(2);
        r2 = new Talon(3);
        encoder = new Encoder(8, 9); // Change ports later if necessary
        solenoidBack = new DoubleSolenoid(4, 5); // CHANGE PORTS LATER
        solenoidFront = new DoubleSolenoid(6, 7); // CHANGE THE PORTS PLEASE
    }

    public void setSpeed(double leftSpeed, double rightSpeed) {
        PID(leftSpeed, l1, l2);
        PID(-rightSpeed, r1, r2);
    }

    public void setSolenoids(Value frontSol, Value backSol) {
        solenoidFront.set(frontSol);
        solenoidBack.set(backSol);
    }

    /*

    int state;
    int counter;

    double targetHeading, targetDistance; // used for automatic movement

    boolean backSolCanPress = true;
    boolean frontSolCanPress = true;

    // perhaps revise the names in the future for the sake of succinctness
    public void update(double leftSpeed, double rightSpeed, boolean frontSolButtonPressed, boolean backSolButtonPressed) {
        PID(leftSpeed, l1, l2);
        PID(rightSpeed, r1, r2);
        if (backSolButtonPressed) { // REVISE THIS METHOD PLEASE
            if (backSolCanPress) {
                if (solenoidBack.get() == DoubleSolenoid.Value.kOff) {
                    solenoidBack.set(DoubleSolenoid.Value.kForward);
                } else if (solenoidBack.get() == DoubleSolenoid.Value.kForward) {
                    solenoidBack.set(DoubleSolenoid.Value.kReverse);
                } else if (solenoidBack.get() == DoubleSolenoid.Value.kReverse) {
                    solenoidBack.set(DoubleSolenoid.Value.kForward);
                }
                backSolCanPress = false;
            } else {
                backSolCanPress = true;
            }
        }
        if (frontSolButtonPressed) {
            if (solenoidFront.get() == DoubleSolenoid.Value.kOff) {
                solenoidFront.set(DoubleSolenoid.Value.kForward);
            }
            if (solenoidFront.get() == DoubleSolenoid.Value.kForward) {
                solenoidFront.set(DoubleSolenoid.Value.kReverse);
            }
            if (solenoidFront.get() == DoubleSolenoid.Value.kReverse) {
                solenoidFront.set(DoubleSolenoid.Value.kForward);
            }
            frontSolCanPress = false;
        } else {
            frontSolCanPress = true;
        }
    }
     */
    private double getRate() {
        return encoder.getRate();
    }
    double maxRate = 9.79; //FIGURE OUT WHAT THE ACTUAL MAXRATE IS ON THE DRIVETRAIN ENCODERS!!!
    double motorVoltage = 0;
    double integral;
    double prevError;

    private void PID(double targetSpeed, Talon... motors) {
    	for (Talon t : motors) {
    		t.set(targetSpeed);
    	}
    	/*
        double error = targetSpeed * maxRate - getRate();
        double P = 0.005;
        double I = 0.00005;
        double D = -0.005;
        if (Math.abs(error) >= 0) {
            motorVoltage += (error * P) + (error - prevError) * D + (integral * I);
            if (motorVoltage > maxRate) {
                motorVoltage = maxRate;
            } else if (motorVoltage < -maxRate) {
                motorVoltage = -maxRate;
            }
            for (Talon t : motors) {
                t.set(motorVoltage / maxRate);
            }
            integral += error;
            prevError = error;
        }
        */
    }
}
