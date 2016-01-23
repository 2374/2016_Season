package org.usfirst.frc.team2374.robot;

import edu.wpi.first.wpilibj.Talon;

public class ChainLift {

    Talon mainMotor;
    double chainLiftSpeed = 0.5; //Change if necessary

    public ChainLift(int motorPort) {
        mainMotor = new Talon(motorPort); //This is a motor
    }

    public void update(boolean chainUpButton, boolean chainDownButton) {
        if (chainUpButton) {
            mainMotor.set(chainLiftSpeed);
        }
        if (chainDownButton) {
            mainMotor.set(-chainLiftSpeed);
        }
    }

}
