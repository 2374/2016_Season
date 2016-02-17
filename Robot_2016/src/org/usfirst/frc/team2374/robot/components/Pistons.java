package org.usfirst.frc.team2374.robot.components;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
import org.usfirst.frc.team2374.robot.Component;
import org.usfirst.frc.team2374.robot.Robot;

public class Pistons extends Component {

    /**
     * The list of all piston modes. A value of true represents kForwards, while
     * a value of false represents kReverse.
     */
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

    /**
     * How many pistons the robot has. For all practical purposes, this number
     * is 4.
     */
    private final int num;

    /**
     * The robot's pistons.
     */
    private final Piston[] pistons;

    /**
     * Creates a set of pistons with solenoids set to the given ports. The given
     * number of ports should be an even number.
     *
     * @param ports The array of ports the solenoids are connected to.
     */
    public Pistons(int... ports) {
        num = ports.length / 2;
        pistons = new Piston[num];
        for (int i = 0; i < num; i++) {
            pistons[i] = new Piston(ports[i * 2], ports[i * 2 + 1]);
        }
    }

    /**
     * Sets the pistons to move to the given values. The given number of values
     * should be equal to the number of pistons.
     *
     * @param values The array of values for the pistons to move to.
     */
    public void setPistons(boolean... values) {
        if (values.length != num) {
            throw new RuntimeException("Bad number of values: received " + values.length + ", needed " + num);
        }
        for (int i = 0; i < num; i++) {
            if (values[i] != pistons[i].value) {
                pistons[i].timer = 0;
                pistons[i].value = values[i];
            }
        }
    }

    /**
     * Sets the pistons to move according to a given piston mode.
     *
     * @param pistonMode The piston mode to follow.
     */
    public void setPistonMode(int pistonMode) {
        setPistons(PISTON_MODES[pistonMode]);
    }

    @Override
    public void update() {
        for (Piston piston : pistons) {
            piston.timer += Robot.deltaTime;
            if (piston.timer > 1) {
                piston.solenoid.set(piston.value ? kForward : kReverse);
            } else {
                piston.solenoid.set(kOff);
            }
        }
    }

    /**
     * An internal class that represents all the data associated with a piston.
     */
    private static class Piston {

        private final DoubleSolenoid solenoid;
        private boolean value;
        private double timer;

        public Piston(int port1, int port2) {
            solenoid = new DoubleSolenoid(port1, port2);
        }
    }
}
