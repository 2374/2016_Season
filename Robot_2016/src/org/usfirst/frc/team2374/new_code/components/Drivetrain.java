package org.usfirst.frc.team2374.new_code.components;

import edu.wpi.first.wpilibj.Talon;
import org.usfirst.frc.team2374.new_code.Component;

public class Drivetrain extends Component {

    /**
     * The robot's four drivetrain motors.
     */
    private final Talon left1, left2, right1, right2;

    /**
     * Creates a drivetrain with motors set to the given ports.
     *
     * @param left1 The port for the front-left motor.
     * @param left2 The port for the back-left motor.
     * @param right1 The port for the front-right motor.
     * @param right2 The port for the back-right motor.
     */
    public Drivetrain(int left1, int left2, int right1, int right2) {
        this.left1 = new Talon(left1);
        this.left2 = new Talon(left2);
        this.right1 = new Talon(right1);
        this.right2 = new Talon(right2);
    }

    /**
     * This function sets the drivetrain to run at a certain speed.
     *
     * @param leftSpeed The speed of the left motors.
     * @param rightSpeed The speed of the right motors.
     */
    public void setSpeed(double leftSpeed, double rightSpeed) {
        left1.set(leftSpeed);
        left2.set(leftSpeed);
        right1.set(rightSpeed);
        right2.set(rightSpeed);
    }

    @Override
    public void update() {
        setSpeed(0, 0);
    }
}
