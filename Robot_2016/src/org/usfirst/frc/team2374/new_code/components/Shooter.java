package org.usfirst.frc.team2374.new_code.components;

import edu.wpi.first.wpilibj.Talon;
import org.usfirst.frc.team2374.new_code.Component;

public class Shooter extends Component {

    /**
     * The robot's two shooter motors.
     */
    private final Talon wheel1, wheel2;

    /**
     * Creates a shooter with motors set to the given ports.
     *
     * @param port1 The port for the first motor.
     * @param port2 The port for the second motor.
     */
    public Shooter(int port1, int port2) {
        wheel1 = new Talon(port1);
        wheel2 = new Talon(port2);
    }

    /**
     * This function sets the shooter to run at a given speed.
     *
     * @param speed The speed of the motors.
     */
    public void setSpeed(double speed) {
        wheel1.set(speed);
        wheel2.set(speed);
    }
}
