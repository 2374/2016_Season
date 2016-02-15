package org.usfirst.frc.team2374.new_code.components;

import edu.wpi.first.wpilibj.Talon;
import org.usfirst.frc.team2374.new_code.Component;

public class Manipulator extends Component {

    /**
     * The robot's manipulator motor.
     */
    private final Talon motor;

    /**
     * Creates a manipulator with motor set to the given port.
     *
     * @param port The port for the motor.
     */
    public Manipulator(int port) {
        motor = new Talon(port);
    }

    /**
     * This function sets the manipulator to run at a given speed.
     *
     * @param speed The speed of the motor.
     */
    public void setSpeed(double speed) {
        motor.set(speed);
    }
}
