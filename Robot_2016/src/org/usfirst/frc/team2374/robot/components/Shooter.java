package org.usfirst.frc.team2374.robot.components;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

import org.usfirst.frc.team2374.robot.Component;

public class Shooter extends Component {

    /**
     * The robot's two shooter motors.
     */
    private final Talon wheel1, wheel2;
    private final Encoder wheelEncoder;

    /**
     * Creates a shooter with motors set to the given ports.
     *
     * @param port1 The port for the first motor.
     * @param port2 The port for the second motor.
     */
    public Shooter(int port1, int port2, int encoderPort1, int encoderPort2) {
        wheel1 = new Talon(port1);
        wheel2 = new Talon(port2);
        wheelEncoder = new Encoder (encoderPort1, encoderPort2, true, EncodingType.k4X);
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

    @Override
    public void update() {
        setSpeed(0);
        SmartDashboard.putNumber("Wheel Encoder Speed",wheelEncoder.getRate());
    }
}
