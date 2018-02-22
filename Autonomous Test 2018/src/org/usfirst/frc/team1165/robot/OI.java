package org.usfirst.frc.team1165.robot;

import org.usfirst.frc.team1165.robot.commands.ResetEncoder;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI
{
	private Joystick stick = new Joystick(RobotMap.JOYSTICK_PORT);

	private JoystickButton resetEncoder = new JoystickButton(stick, RobotMap.RESET_BUTTON);
	// private JoystickButton rotateButton = new JoystickButton(stick,
	// RobotMap.ROTATE_BUTTON);
	// private JoystickButton driveStraightButton = new JoystickButton(stick,
	// RobotMap.DRIVE_STRAIGHT_BUTTON);

	public OI()
	{
		resetEncoder.whenPressed(new ResetEncoder());
		// rotateButton.whenPressed(new RotateToRelHeading(90));
		// driveStraightButton.whenPressed(new DriveStraight(15));
	}

	public double getX()
	{
		return dampen(stick.getX());
	}

	public double getY()
	{
		return dampen(-stick.getY());
	}

	public double getTwist()
	{
		return dampen(stick.getTwist());
	}

	public double dampen(double value)
	{
		double damped = Math.pow(value, 3);
		return Math.abs(damped) < 0.1 ? 0 : damped;
	}

	public void report()
	{
		SmartDashboard.putNumber("OI X", getX());
		SmartDashboard.putNumber("OI Y", getY());
		SmartDashboard.putNumber("OI Twist", getTwist());
	}
}