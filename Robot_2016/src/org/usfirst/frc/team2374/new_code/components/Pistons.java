package org.usfirst.frc.team2374.new_code.components;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import org.usfirst.frc.team2374.new_code.Component;

public class Pistons extends Component {

    /**
     * The robot's four piston solenoids.
     */
    private final DoubleSolenoid frontLeft, frontRight, backLeft, backRight;

    /**
     * Creates a set of pistons with solenoids set to the given ports.
     *
     * @param frontLeft1 The forward port for the front-left solenoid.
     * @param frontLeft2 The reverse port for the front-left solenoid.
     * @param frontRight1 The forward port for the front-right solenoid.
     * @param frontRight2 The reverse port for the front-right solenoid.
     * @param backLeft1 The forward port for the back-left solenoid.
     * @param backLeft2 The reverse port for the back-left solenoid.
     * @param backRight1 The forward port for the back-right solenoid.
     * @param backRight2 The reverse port for the back-right solenoid.
     */
    public Pistons(int frontLeft1, int frontLeft2, int frontRight1, int frontRight2, int backLeft1, int backLeft2, int backRight1, int backRight2) {
        frontLeft = new DoubleSolenoid(frontLeft1, frontLeft2);
        frontRight = new DoubleSolenoid(frontRight1, frontRight2);
        backLeft = new DoubleSolenoid(backLeft1, backLeft2);
        backRight = new DoubleSolenoid(backRight1, backRight2);
    }

    /**
     * This function sets the pistons to move according to given values.
     *
     * @param frontLeft The value of the front-left piston.
     * @param frontRight The value of the front-right piston.
     * @param backLeft The value of the back-left piston.
     * @param backRight The value of the back-right piston.
     */
    public void setPistons(Value frontLeft, Value frontRight, Value backLeft, Value backRight) {
        this.frontLeft.set(frontLeft);
        this.frontRight.set(frontRight);
        this.backLeft.set(backLeft);
        this.backRight.set(backRight);
    }
}
