package org.usfirst.frc.team2374.robot;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

public class Intake {

    int intakePort;
    Talon intakeTalon;
    double intakeSpeed = 0.5;//Change later if necessary
    
    public boolean forcedDown;

    public Intake(int port) {
        intakeTalon = new Talon(port);
    }

    public void update(boolean intakeForwardButton, boolean intakeReverseButton) {
    	if (forcedDown) {
    		intakeTalon.set(-intakeSpeed);
    		return;
    	}
        if (intakeForwardButton) {
            intakeTalon.set(intakeSpeed);
        }
        else if (intakeReverseButton) {
            intakeTalon.set(-intakeSpeed);
        }
        else
        	intakeTalon.set(0);
    }
}
