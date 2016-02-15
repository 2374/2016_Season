package org.usfirst.frc.team2374.new_code;

import java.util.function.Supplier;
import org.usfirst.frc.team2374.new_code.controllers.AutonomousController;
import org.usfirst.frc.team2374.new_code.controllers.DisabledController;
import org.usfirst.frc.team2374.new_code.controllers.TeleopController;
import org.usfirst.frc.team2374.new_code.controllers.TestController;

public abstract class Controller {

    /**
     * This function is called once when the controller starts.
     */
    public abstract void start();

    /**
     * This enum lists all the types of controllers. It is used internally to
     * determine which controller to run.
     */
    public static enum ControllerType {

        AUTONOMOUS(AutonomousController::new),
        DISABLED(DisabledController::new),
        TELEOP(TeleopController::new),
        TEST(TestController::new);

        public static ControllerType getType() {
            if (Robot.robot.isDisabled()) {
                return DISABLED;
            }
            if (Robot.robot.isAutonomous()) {
                return AUTONOMOUS;
            }
            if (Robot.robot.isOperatorControl()) {
                return TELEOP;
            }
            if (Robot.robot.isTest()) {
                return TEST;
            }
            throw new RuntimeException("Unknown robot state");
        }

        private final Supplier<Controller> creator;

        private ControllerType(Supplier<Controller> creator) {
            this.creator = creator;
        }

        public Controller create() {
            return creator.get();
        }
    }
}
