package org.usfirst.frc.team1165.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap
{
	public static final int JOYSTICK_PORT = 0;

	public static final int RESET_BUTTON = 11;
	public static final int ROTATE_BUTTON = 9;
	public static final int DRIVE_STRAIGHT_BUTTON = 10;

	public static final int DRIVE_TALON_FRONT_LEFT = 0;
	public static final int DRIVE_TALON_REAR_LEFT = 1;
	public static final int DRIVE_TALON_FRONT_RIGHT = 2;
	public static final int DRIVE_TALON_REAR_RIGHT = 3;
}
