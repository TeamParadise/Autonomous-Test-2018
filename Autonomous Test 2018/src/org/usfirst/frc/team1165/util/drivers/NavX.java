/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1165.util.drivers;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class NavX implements PIDSource
{
	private static final AHRS mAHRS = new AHRS(SerialPort.Port.kMXP);

	public static void reset()
	{
		mAHRS.reset();
	}

	public static double getAngle()
	{
		return Math.abs(mAHRS.getAngle()) % 360;
	}

	public static double getFusedHeading()
	{
		return mAHRS.getFusedHeading();
	}

//	public static double convertFromG(double g) {
//		return g * 32.174049;
//	}
//	
//	public static double getAccelX() {
//		return convertFromG(mAHRS.getWorldLinearAccelX());
//	}
//	
//	public static double getAccelY() {
//		return convertFromG(mAHRS.getWorldLinearAccelY());
//	}
//	
//	public static double getAccelZ() {
//		return convertFromG(mAHRS.getWorldLinearAccelZ());
//	}
	
	public static void report()
	{
		SmartDashboard.putNumber("NavX Angle", getAngle());
		SmartDashboard.putNumber("NavX Fused Heading", getFusedHeading());
		SmartDashboard.putNumber("NavX Yaw", mAHRS.getYaw());
//		SmartDashboard.putNumber("NavX Accel X", getAccelX());
//		SmartDashboard.putNumber("NavX Accel Y", getAccelY());
//		SmartDashboard.putNumber("NavX Accel Z", getAccelZ());
	}

	///////////////////// PID SOURCE METHODS
	
	@Override
	public void setPIDSourceType(PIDSourceType pidSource)
	{
	}

	@Override
	public PIDSourceType getPIDSourceType()
	{
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet()
	{
		return getFusedHeading();
	}
}
