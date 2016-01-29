package org.usfirst.frc.team2374.robot;

import edu.wpi.first.wpilibj.Talon;

public class Manipulator {

    Talon mainMotor;
    double manipulatorSpeed = 0.5; //Change if necessary

    public Manipulator(int motorPort) {
        mainMotor = new Talon(motorPort); //This is a motor
    }

    public void update(boolean upButton, boolean downButton) {
        if (upButton) {
            mainMotor.set(manipulatorSpeed);
        }
        else if (downButton) {
            mainMotor.set(-manipulatorSpeed);
        }
        else{
        	mainMotor.set(0);
        }
    }
}