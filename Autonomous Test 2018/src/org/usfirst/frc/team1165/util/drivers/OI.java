package org.usfirst.frc.team1165.util.drivers;

import org.usfirst.frc.team1165.robot.RobotMap;
import org.usfirst.frc.team1165.robot.commands.ResetEncoder;
import org.usfirst.frc.team1165.robot.commands.auto.RotateToRelHeading;
import org.usfirst.frc.team1165.robot.commands.auto.ZeroThenDriveStraight;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI
{
	private static Joystick stick = new Joystick(RobotMap.JOYSTICK_PORT);

//	private static JoystickButton resetEncoder = new JoystickButton(stick, RobotMap.RESET_BUTTON);
	// private JoystickButton rotateButton = new JoystickButton(stick,
	// RobotMap.ROTATE_BUTTON);
	// private JoystickButton driveStraightButton = new JoystickButton(stick,
	// RobotMap.DRIVE_STRAIGHT_BUTTON);

	static
	{
//		resetEncoder.whenPressed(new ResetEncoder());
		
//		SmartDashboard.putData(new ResetEncoder());
//		SmartDashboard.putData(new ZeroThenDriveStraight(15));
		SmartDashboard.putData(new RotateToRelHeading(90));
		// rotateButton.whenPressed(new RotateToRelHeading(90));
		// driveStraightButton.whenPressed(new DriveStraight(15));
	}

	public static double getX()
	{
		return dampen(stick.getX());
	}

	public static double getY()
	{
		return dampen(-stick.getY());
	}

	public static double getTwist()
	{
		return dampen(stick.getTwist());
	}

	public static double dampen(double value)
	{
		double damped = Math.pow(value, 3);
		return Math.abs(damped) < 0.1 ? 0 : damped;
	}

	public static void report()
	{
		SmartDashboard.putNumber("OI X", getX());
		SmartDashboard.putNumber("OI Y", getY());
		SmartDashboard.putNumber("OI Twist", getTwist());
	}
}