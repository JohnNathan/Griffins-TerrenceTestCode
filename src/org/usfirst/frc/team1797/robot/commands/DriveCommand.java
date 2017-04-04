package org.usfirst.frc.team1797.robot.commands;

import org.usfirst.frc.team1797.robot.Robot;
import org.usfirst.frc.team1884.util.input.XBoxController;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveCommand extends Command {

    public DriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	XBoxController driverController = Robot.oi.getDriverController();
    	double[] xy_vals = normalize(driverController.r_x_scaled(), driverController.l_y_scaled());
    	driverController = null;
    	
    	double x = xy_vals[0];
    	double y = xy_vals[1];
    	
    	Robot.InstanceMap.driveTrain.setLeftSpeed(y+x);
    	Robot.InstanceMap.driveTrain.setRightSpeed(-y+x);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    private double[] normalize(double... in) {
    	double max = 1.0;
    	for (double d : in) if (Math.abs(d) > max) max = Math.abs(d);
    	double[] ret = new double[in.length];
    	for (int i = 0; i < ret.length; ++i) ret[i] = in[i]/max;
    	return ret;
    }
    
}
