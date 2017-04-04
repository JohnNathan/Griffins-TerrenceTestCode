package org.usfirst.frc.team1884.util.input;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class XBoxController extends edu.wpi.first.wpilibj.Joystick {

	public XBoxController(int port) {
		super(port);
	}
	
	private static final int ky = -1, kx = 1;
	
	private static final double k_deadzone = .12, k_upperzone = .11; // 1 minus the coordinate of the upper dead zone
	
	public double l_y() {
		return ky*super.getRawAxis(1);
	}
	
	public double l_x() {
		return kx*super.getRawAxis(0);
	}
	
	public double r_y() {
		return ky*super.getRawAxis(5);
	}
	
	public double r_x() {
		return kx*super.getRawAxis(4);
	}
	
	private double scale(double raw) {
		double val = (Math.abs(raw)-k_deadzone) / (1 - k_deadzone - k_upperzone);
		return raw > 1 - k_upperzone ? 1.0 : raw < k_upperzone - 1 ? -1.0 : raw > k_deadzone ? Math.pow(val, 3) : raw < - k_deadzone ? -Math.pow(val, 3) : 0.0;
	}
	
	public double l_y_scaled() {
		return scale(l_y());
	}
	
	public double l_x_scaled() {
		return scale(l_x());
	}
	
	public double r_y_scaled() {
		return scale(r_y());
	}
	
	public double r_x_scaled() {
		return scale(r_x());
	}
	
	public double lt_a() {
		return super.getRawAxis(2);
	}
	
	public double rt_a() {
		return super.getRawAxis(3);
	}
	
	public JoystickButton a = new JoystickButton(this, 1);
	public boolean a() {
		return super.getRawButton(1);
	}
	
	public JoystickButton b = new JoystickButton(this, 2);
	public boolean b() {
		return super.getRawButton(2);
	}
	
	public JoystickButton x = new JoystickButton(this, 3);
	public boolean x() {
		return super.getRawButton(3);
	}
	
	public JoystickButton y = new JoystickButton(this, 4);
	public boolean y() {
		return super.getRawButton(4);
	}
	
	public JoystickButton lb = new JoystickButton(this, 5);
	public boolean lb() {
		return super.getRawButton(5);
	}
	
	public JoystickButton rb = new JoystickButton(this, 6);
	public boolean rb() {
		return super.getRawButton(6);
	}
	
	public JoystickButton back = new JoystickButton(this, 7);
	public boolean back() {
		return super.getRawButton(7);
	}
	
	public JoystickButton start = new JoystickButton(this, 8);
	public boolean start() {
		return super.getRawButton(8);
	}
	
	public JoystickButton l_stick = new JoystickButton(this, 9);
	public boolean l_stick() {
		return super.getRawButton(9);
	}
	
	public JoystickButton r_stick = new JoystickButton(this, 10);
	public boolean r_stick() {
		return super.getRawButton(10);
	}
	
	public Trigger lt_b = new Trigger() {
		@Override
		public boolean get() {
			return getRawAxis(2) > 0.9;
		}
	};
	public boolean lt_b() {
		return super.getRawAxis(2) > 0.9;
	}
	
	public Trigger rt_b = new Trigger() {
		@Override
		public boolean get() {
			return getRawAxis(3) > 0.9;
		}
	};
	public boolean rt_b() {
		return super.getRawAxis(3) > 0.9;
	}
	
	//TODO make triggers for POV
	public int pov() {
		return super.getPOV();
	}
	
}
