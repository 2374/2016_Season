package org.usfirst.frc.team2374.robot.components;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2374.robot.Component;
import org.usfirst.frc.team2374.robot.PIDComponent;

public class Drivetrain extends Component {

    /**
     * The robot's four drivetrain motors.
     */
    private final Talon left1, left2, right1, right2;
    private final Encoder encoderLeft, encoderRight;
    //private final PIDComponent DrivetrainPID;

    /**
     * Creates a drivetrain with motors set to the given ports.
     *
     * @param left1 The port for the front-left motor.
     * @param left2 The port for the back-left motor.
     * @param right1 The port for the front-right motor.
     * @param right2 The port for the back-right motor.
     */
    public Drivetrain(int left1, int left2, int right1, int right2,
    		int encoderLeftPort1, int encoderLeftPort2, int encoderRightPort1, int encoderRightPort2/*, double p, double i, double d*/) { //IGNORE THOSE PARAMETERS WE TRIED TO IMPLEMENT PID AND IT DIDN'T WORK BLAME FIRST
        this.left1 = new Talon(left1);
        this.left2 = new Talon(left2);
        this.right1 = new Talon(right1);
        this.right2 = new Talon(right2);
        encoderLeft = new Encoder(encoderLeftPort1,encoderLeftPort2);
        encoderRight = new Encoder(encoderRightPort1,encoderRightPort2);
        //DrivetrainPID = new PIDComponent(p,i,d);
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

    public Encoder getRightEncoder(){
    	return encoderRight;
    }
    
    public Encoder getLeftEncoder(){
    	return encoderLeft;
    }
    
    @Override
    public void update() {
        setSpeed(0, 0);
        SmartDashboard.putNumber("Left Encoder Speed",encoderLeft.getRate());
        SmartDashboard.putNumber("Right Encoder Speed",encoderRight.getRate());
    }
}
