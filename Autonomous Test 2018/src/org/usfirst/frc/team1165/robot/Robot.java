/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1165.robot;

import org.usfirst.frc.team1165.robot.subsystems.DriveStraightPID;
import org.usfirst.frc.team1165.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1165.robot.subsystems.RotateToRelHeadingPID;
import org.usfirst.frc.team1165.util.drivers.NavX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot
{
	public static DriveTrain mDriveTrain = new DriveTrain();
	public static DriveStraightPID mDriveStraightPID = new DriveStraightPID();
//	public static DriveTrainPID mDriveTrainPID = new DriveTrainPID();
	public static RotateToRelHeadingPID mRotatePID = new RotateToRelHeadingPID();
//
//	private static SendableChooser<Command> autoChooser = new SendableChooser<Command>();
//	private static Command autonomousCommand;
//
//	private static Preferences mPrefs = Preferences.getInstance();
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit()
	{
//		mPrefs.putBoolean("Reporting", true);
		
//		autoChooser.addObject("Drive Straight (45)", new DriveStraight(90));
//		autoChooser.addObject("Auto Test", new AutoTest());
//		autoChooser.addDefault("Rotate to Relative Heading (90)", new RotateToRelHeading(90));

//		SmartDashboard.putData("Auto Mode", autoChooser);
	}

	@Override
	public void robotPeriodic()
	{
//		if(mPrefs.getBoolean("Reporting", true))
//		{
//			mDriveTrain.report();
//			mDriveStraightPID.report();
//			mDriveTrainPID.report();
//			mRotatePID.report();

			NavX.report();
//			OI.report();
//		}
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit()
	{
	}

	@Override
	public void disabledPeriodic()
	{
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit()
	{
//		autonomousCommand = autoChooser.getSelected();
//
//		if (autonomousCommand != null)
//			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic()
	{
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit()
	{
//		if (autonomousCommand != null)
//			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic()
	{
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic()
	{
	}
}
