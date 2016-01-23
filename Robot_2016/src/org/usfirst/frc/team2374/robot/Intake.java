package org.usfirst.frc.team2374.robot;

import edu.wpi.first.wpilibj.Talon;

public class Intake {

    int intakePort;
    Talon intakeTalon;
    double intakeSpeed = 0.5;//Change later if necessary

    public Intake(int port) {
        intakeTalon = new Talon(port);
    }

    public void update(boolean intakeForwardButton, boolean intakeReverseButton) {
        if (intakeForwardButton) {
            intakeTalon.set(intakeSpeed);
        }
        if (intakeReverseButton) {
            intakeTalon.set(-intakeSpeed);
        }
    }
}
