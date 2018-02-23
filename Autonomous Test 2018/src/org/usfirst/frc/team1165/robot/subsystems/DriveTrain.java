/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1165.robot.subsystems;

import static org.usfirst.frc.team1165.robot.RobotMap.DRIVE_TALON_FRONT_LEFT;
import static org.usfirst.frc.team1165.robot.RobotMap.DRIVE_TALON_FRONT_RIGHT;
import static org.usfirst.frc.team1165.robot.RobotMap.DRIVE_TALON_REAR_LEFT;
import static org.usfirst.frc.team1165.robot.RobotMap.DRIVE_TALON_REAR_RIGHT;

import org.usfirst.frc.team1165.robot.commands.DriveWithJoystick;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * An example subsystem. You can replace me with your own Subsystem.
 */
public class DriveTrain extends Subsystem implements PIDSource
{
	private WPI_TalonSRX mFrontLeft = new WPI_TalonSRX(DRIVE_TALON_FRONT_LEFT);
	private WPI_TalonSRX mRearLeft = new WPI_TalonSRX(DRIVE_TALON_REAR_LEFT);
	private WPI_TalonSRX mFrontRight = new WPI_TalonSRX(DRIVE_TALON_FRONT_RIGHT);
	private WPI_TalonSRX mRearRight = new WPI_TalonSRX(DRIVE_TALON_REAR_RIGHT);

	private SpeedControllerGroup mLeftDrive = new SpeedControllerGroup(mFrontLeft, mRearLeft);
	private SpeedControllerGroup mRightDrive = new SpeedControllerGroup(mFrontRight, mRearRight);

	private DifferentialDrive mDrive = new DifferentialDrive(mLeftDrive, mRightDrive);

	public void initDefaultCommand()
	{
		setDefaultCommand(new DriveWithJoystick());
	}

	public void arcadeDrive(double y, double twist)
	{
		mDrive.arcadeDrive(y, twist, false);
	}
	
	public void arcadeDriveDamped(double y, double twist)
	{
		mDrive.arcadeDrive(y, twist);
	}

	public void tankDriveLeft(double output)
	{
		mLeftDrive.set(output);
	}

	public void tankDriveRight(double output)
	{
		mRightDrive.set(output);
	}

	public void stop()
	{
		arcadeDrive(0, 0);
	}

	public void resetEncoder()
	{
		mFrontRight.getSensorCollection().setQuadraturePosition(0, 0);
	}

	public double getPosition()
	{
		return (mFrontRight.getSensorCollection().getQuadraturePosition() * Math.PI * 6) / 4096;
	}

	public double getVelocity()
	{
		return (mFrontRight.getSensorCollection().getQuadratureVelocity() * Math.PI * 6) / 4096;
	}

	public boolean isZeroed() {
		return Math.abs(getPosition()) < 0.01;
	}
	
	//////////////////////////////// PID STUFF

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
		return getPosition();
	}

	public void report()
	{
		SmartDashboard.putNumber(getName() + " Front Left Speed", mFrontLeft.get());
//		SmartDashboard.putNumber(getName() + " Rear Left Speed", mRearLeft.get());
//		SmartDashboard.putNumber(getName() + " Front Right Speed", mFrontRight.get());
//		SmartDashboard.putNumber(getName() + " Rear Right Speed", mRearRight.get());

		SmartDashboard.putNumber(getName() + " Position", getPosition());
		SmartDashboard.putNumber(getName() + " Velocity", getVelocity());
	}
}
