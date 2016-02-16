package org.usfirst.frc.team2374.new_code.sensors;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import org.usfirst.frc.team2374.new_code.Robot;
import org.usfirst.frc.team2374.new_code.Robot.RobotSystem;
import org.usfirst.frc.team2374.new_code.math.Vec3;

public class PositionTracker extends RobotSystem {

    /**
     * The robot's built-in accelerometer.
     */
    private final BuiltInAccelerometer accelerometer;

    /**
     * The robot's gyroscope.
     */
    private final AnalogGyro gyroscope;

    /**
     * The robot's current position (in meters).
     */
    public Vec3 position;

    /**
     * The robot's current velocity (in meters/second).
     */
    public Vec3 velocity;

    /**
     * The robot's current acceleration (in meters/second^2).
     */
    public Vec3 acceleration;

    /**
     * Creates a new position tracker with gyroscope set to the given port.
     *
     * @param gyroPort The port for the gyroscope.
     */
    public PositionTracker(int gyroPort) {
        accelerometer = new BuiltInAccelerometer();
        gyroscope = new AnalogGyro(new AnalogInput(gyroPort));
        reset();
    }

    /**
     * This function gets the current direction of the robot (in radians).
     *
     * @return The current direction of the robot (in radians).
     */
    public double direction() {
        return gyroscope.getAngle() * Math.PI / 180;
    }

    /**
     * This function resets the position tracker's data.
     */
    public void reset() {
        gyroscope.reset();
        position = new Vec3(0);
        velocity = new Vec3(0);
        acceleration = new Vec3(0);
    }

    @Override
    public void update() {
        Vec3 rawA = new Vec3(accelerometer.getX(), accelerometer.getY(), accelerometer.getZ());
        acceleration = rawA.multiply(9.8).rotateAboutZ(direction());
        velocity = velocity.add(acceleration.multiply(Robot.deltaTime));
        position = position.add(velocity.multiply(Robot.deltaTime));
    }
}