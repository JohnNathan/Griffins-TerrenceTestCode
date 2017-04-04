package org.usfirst.frc.team1797.robot;

import org.usfirst.frc.team1884.util.input.XBoxController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	private XBoxController driverController;
	
	public OI() {
		driverController = new XBoxController(0);
	}
	
	public XBoxController getDriverController() {
		return driverController;
	}
	
	public boolean getRecordingButton() {
		return driverController.lb();
	}
}
