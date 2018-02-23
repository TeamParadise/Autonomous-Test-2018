package org.usfirst.frc.team1165.robot.commands;

import org.usfirst.frc.team1165.robot.Robot;
import org.usfirst.frc.team1165.util.drivers.OI;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithJoystick extends Command
{
	public DriveWithJoystick()
	{
		requires(Robot.mDriveTrain);
	}

	@Override
	protected void execute()
	{
		Robot.mDriveTrain.arcadeDrive(OI.getY(), OI.getTwist());
		//DriverStation.reportWarning(Double.toString(OI.getY()), false);
	}

	@Override
	protected boolean isFinished()
	{
		return false;
	}

	@Override
	protected void end()
	{
		Robot.mDriveTrain.stop();
	}
}
