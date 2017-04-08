package org.usfirst.frc.team1797.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class FooSystem extends Subsystem implements Recordable {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public FooSystem() {
	}
	
	public boolean isFoo = false;
	
	public void openFoo() {
		System.out.println("Open");
		isFoo = true;
	}
	
	public void closeFoo() {
		System.out.println("Close");
		isFoo = false;
	}
	

    public void initDefaultCommand() {
    }


    // open/close=1/-1
	@Override
	public double[] getData() {
		return new double[] {isFoo ? 1.0 : -1.0};
	}

	@Override
	public void putData(double[] data) {
		boolean openclose = data[0]>0.0;
		if(openclose && openclose != prevData) {
			openFoo();
		} else if (!openclose && openclose != prevData) {
			closeFoo();
		}
		prevData=openclose;
	}
	private boolean prevData = false;

	@Override
	public int getLength() {
		return 1;
	}
}

