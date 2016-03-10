package org.usfirst.frc.team2374.robot.components;

import org.usfirst.frc.team2374.robot.Component;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

public class Shooter extends Component {

	/**
	 * The robot's two shooter motors.
	 */

	// private PIDComponent shooterPID;
	private final Talon wheel1, wheel2;
	private final Encoder wheelEncoder;
	double prevError = 0;
	double motorVoltage = 0;
	double integral = 0;

	/**
	 * Creates a shooter with motors set to the given ports.
	 *
	 * @param port1
	 *            The port for the first motor.
	 * @param port2
	 *            The port for the second motor.
	 */
	public Shooter(int port1, int port2, int encoderPort1, int encoderPort2
	/* ,double p, double i, double d */) {
		wheel1 = new Talon(port1);
		wheel2 = new Talon(port2);
		wheelEncoder = new Encoder(encoderPort1, encoderPort2);// the encoding
																// type might be
																// the reason we
		// aren't getting encoder values
		// shooterPID = new PIDComponent(p, i, d);
	}

	/*
	 * public double returnPIDInput() { return shooterPID.returnPIDInput(); }
	 * 
	 * public void usePIDOutput(double output) { }
	 */

	/**
	 * This function sets the shooter to run at a given speed.
	 *
	 * @param speed
	 *            The speed of the motors.
	 */
	public void setSpeed(double speed) {
		wheel1.set(speed);
		wheel2.set(speed);
	}

	public double getRate() {
		return wheelEncoder.getRate();
	}

	@Override
	public void update() {
		// SmartDashboard.putNumber("Wheel Encoder Speed",
		// wheelEncoder.getRate());
	}

	public void PIDShooter(double targetSpeed) {
		double maxRate = 9.79;
		double error = targetSpeed * maxRate - getRate();
		double P = 0.01;
		double I = 0.00005;
		double D = -0.005;
		if (Math.abs(error) >= 0) {
			motorVoltage += (error * P) + (error - prevError) * D
					+ (integral * I);
			if (motorVoltage > maxRate) {
				motorVoltage = maxRate;
			} else if (motorVoltage < -maxRate) {
				motorVoltage = -maxRate;
			}
			wheel1.set(motorVoltage / maxRate);
			wheel2.set(motorVoltage / maxRate);
			integral += error;
			prevError = error;
		}
	}

	public void resetPid() {
		prevError = 0;
		integral = 0;
		motorVoltage = 0;
	}
}
