/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1165.robot.subsystems;

import org.usfirst.frc.team1165.robot.RobotMap;
import org.usfirst.frc.team1165.robot.commands.DriveWithJoystick;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveTrain extends Subsystem
{
	private MecanumDrive robotDrive;

	private WPI_TalonSRX frontLeft;
	private WPI_TalonSRX rearLeft;
	private WPI_TalonSRX frontRight;
	private WPI_TalonSRX rearRight;
	
	private double initLeftPosition = 0;
	private double initRightPosition = 0;
	
	public DriveTrain()
	{
		frontLeft = new WPI_TalonSRX(RobotMap.FRONT_LEFT_PORT);
		rearLeft = new WPI_TalonSRX(RobotMap.REAR_LEFT_PORT);
		frontRight = new WPI_TalonSRX(RobotMap.FRONT_RIGHT_PORT);
		rearRight = new WPI_TalonSRX(RobotMap.REAR_RIGHT_PORT);
		
		robotDrive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);
		
		resetLeftEncoder();
	}
	
	public void initDefaultCommand()
	{
		 setDefaultCommand(new DriveWithJoystick());
	}
	
	public void drive(double x, double y, double twist, double angle)
	{
		robotDrive.driveCartesian(x, y, twist, angle);
	}
	
	public void tankDriveLeft(double output)
	{
		frontLeft.set(output);
		rearLeft.set(output);
	}
	
	public void tankDriveRight(double output)
	{
		frontRight.set(-output);
		rearRight.set(-output);
	}
	
    /////////////////////////////////// ENCODER STUFF ///////////////////////////////////

	public void resetLeftEncoder()
	{
		initLeftPosition = getLeftPositionInches();
	}
	
	public double getLeftPositionInches()
	{
		return (-frontLeft.getSensorCollection().getQuadraturePosition() * Math.PI * 6) / 4096;
	}
	
	public double getLeftDistanceInches()
	{
		return getLeftPositionInches() - initLeftPosition;
	}

	public void resetRightEncoder()
	{
		initRightPosition = getRightPositionInches();
	}
	
	public double getRightPositionInches()
	{
		return (frontRight.getSensorCollection().getQuadraturePosition() * Math.PI * 6) / 4096;
	}
	
	public double getRightDistanceInches()
	{
		return getRightPositionInches() - initRightPosition;
	}
	
	public double getAverageEncoderDistance() {
		return (getLeftDistanceInches() + getRightDistanceInches()) / 2.0;
	}
	
	public void resetEncoders()
	{
		resetLeftEncoder();
		resetRightEncoder();
	}
	
    //////////////////////////////// END OF ENCODER STUFF ////////////////////////////////

	public void report()
	{
		SmartDashboard.putNumber("Front Left Speed", frontLeft.get());
		SmartDashboard.putNumber("Rear Left Speed", rearLeft.get());
		SmartDashboard.putNumber("Front Right Speed", frontRight.get());
		SmartDashboard.putNumber("Rear Right Speed", rearRight.get());

		SmartDashboard.putNumber("Left Quad Pos", getLeftDistanceInches());
		SmartDashboard.putNumber("Right Quad Pos", getRightDistanceInches());

//		SmartDashboard.putNumber("Front Left Init Position", initLeftPosition);
		
		SmartDashboard.putNumber("Drive Train Avg Enc Dist", getAverageEncoderDistance());
	}
}
