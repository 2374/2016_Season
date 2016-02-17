package org.usfirst.frc.team2374.robot.components;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
import org.usfirst.frc.team2374.robot.Component;
import org.usfirst.frc.team2374.robot.Robot;

public class Pistons extends Component {

    /**
     * The robot's four pistons.
     */
    private final Piston[] pistons = new Piston[4];

    public Pistons(int... ports) {

    }

//    /**
//     * The robot's four piston solenoids.
//     */
//    private final DoubleSolenoid frontLeft, frontRight, backLeft, backRight;
//
//    /**
//     * The values the pistons should be set at.
//     */
//    private boolean frontLeftValue, frontRightValue, backLeftValue, backRightValue;
//
//    /**
//     * How long ago each piston was set at its current value.
//     */
//    private double frontLeftTimer, frontRightTimer, backLeftTimer, backRightTimer;
//
//    /**
//     * Creates a set of pistons with solenoids set to the given ports.
//     *
//     * @param frontLeft1 The forward port for the front-left solenoid.
//     * @param frontLeft2 The reverse port for the front-left solenoid.
//     * @param frontRight1 The forward port for the front-right solenoid.
//     * @param frontRight2 The reverse port for the front-right solenoid.
//     * @param backLeft1 The forward port for the back-left solenoid.
//     * @param backLeft2 The reverse port for the back-left solenoid.
//     * @param backRight1 The forward port for the back-right solenoid.
//     * @param backRight2 The reverse port for the back-right solenoid.
//     */
//    public Pistons(int frontLeft1, int frontLeft2, int frontRight1, int frontRight2, int backLeft1, int backLeft2, int backRight1, int backRight2) {
//        frontLeft = new DoubleSolenoid(frontLeft1, frontLeft2);
//        frontRight = new DoubleSolenoid(frontRight1, frontRight2);
//        backLeft = new DoubleSolenoid(backLeft1, backLeft2);
//        backRight = new DoubleSolenoid(backRight1, backRight2);
//    }
    /**
     * This function sets the pistons to move according to given values.
     *
     * @param frontLeft The value of the front-left piston.
     * @param frontRight The value of the front-right piston.
     * @param backLeft The value of the back-left piston.
     * @param backRight The value of the back-right piston.
     */
    public void setPistons(boolean frontLeft, boolean frontRight, boolean backLeft, boolean backRight) {
        if (frontLeftValue != frontLeft) {
            frontLeftTimer = 0;
        }
        if (frontRightValue != frontRight) {
            frontRightTimer = 0;
        }
        if (backLeftValue != backLeft) {
            backLeftTimer = 0;
        }
        if (backRightValue != backRight) {
            backRightTimer = 0;
        }
        frontLeftValue = frontLeft;
        frontRightValue = frontRight;
        backLeftValue = backLeft;
        backRightValue = backRight;
    }

    private static final boolean[][] PISTON_MODES = new boolean[][]{
        {false, false, false, false},//all off
        {true, true, true, true},//all on
        {true, true, false, false},//front on
        {false, false, true, true},//back on
        {true, false, true, false},//left on
        {false, true, false, true},//right on
        {false, true, true, false},//front right back left
        {true, false, false, true},//front left back right
        {true, true, true, false},//back right off
        {true, true, false, true},//back left off
        {true, false, true, true},//front right off
        {false, true, true, true}//front left off
    };

    public void setPistonMode(int pistonMode) {
        setPistons(PISTON_MODES[pistonMode][0], PISTON_MODES[pistonMode][1], PISTON_MODES[pistonMode][2], PISTON_MODES[pistonMode][3]);
    }

    @Override
    public void update() {
        frontLeftTimer += Robot.deltaTime;
        frontRightTimer += Robot.deltaTime;
        backLeftTimer += Robot.deltaTime;
        backRightTimer += Robot.deltaTime;

        frontLeft.set(kOff);

        if (frontLeftTimer < 1) {
            frontLeft.set(frontLeftValue ? kForward : kReverse);
        }
    }

    private static class Piston {

        private DoubleSolenoid solenoid;
        private boolean value;
        private double timer;
    }
}
