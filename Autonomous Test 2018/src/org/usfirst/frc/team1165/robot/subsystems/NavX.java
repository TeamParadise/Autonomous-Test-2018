/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1165.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class NavX extends Subsystem
{
	private AHRS ahrs;

	public NavX()
	{
		try
		{
			ahrs = new AHRS(SerialPort.Port.kMXP);
		} catch (RuntimeException ex)
		{
			DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
		}
	}

	@Override
	public void initDefaultCommand()
	{
		// setDefaultCommand(new DriveWithJoystick());
	}

	public void reset()
	{
		ahrs.reset();
	}

	public double getAngle()
	{
		return ahrs.getAngle();
	}

	public double getFusedHeading()
	{
		// return getAngle() % 360;
		return ahrs.getFusedHeading(); // needs to be calibrated
	}

	public void report()
	{
		SmartDashboard.putNumber("NavX Angle", ahrs.getAngle());
		SmartDashboard.putNumber("NavX Angle Modulus", ahrs.getAngle() % 360);
		SmartDashboard.putNumber("NavX Fused Heading", ahrs.getFusedHeading());
	}
}
