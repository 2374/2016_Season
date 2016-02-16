package org.usfirst.frc.team2374.robot.components;

import edu.wpi.first.wpilibj.Talon;
import org.usfirst.frc.team2374.robot.Component;

public class Intake extends Component {

    /**
     * The robot's intake motor.
     */
    private final Talon motor;

    /**
     * Creates an intake with motor set to the given port.
     *
     * @param port The port for the motor.
     */
    public Intake(int port) {
        motor = new Talon(port);
    }

    /**
     * This function sets the intake to run at a given speed.
     *
     * @param speed The speed of the motor.
     */
    public void setSpeed(double speed) {
        motor.set(speed);
    }

    @Override
    public void update() {
        setSpeed(0);
    }
}
