package org.usfirst.frc.team2374.robot.math;

public abstract class MathUtil {

    public static boolean angleNear(double angle1, double angle2, double maxError) {
        double diff = Math.abs(angle1 - angle2) % (2 * Math.PI);
        return diff < maxError || diff > 2 * Math.PI - maxError;
    }

    public static boolean valueNear(double value1, double value2, double maxError) {
        return Math.abs(value1 - value2) < maxError;
    }
}
