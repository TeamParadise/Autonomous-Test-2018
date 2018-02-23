package org.usfirst.frc.team1165.robot.commands.auto;

import org.usfirst.frc.team1165.robot.commands.ResetEncoder;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ZeroThenDriveStraight extends CommandGroup
{
	public ZeroThenDriveStraight(double target)
	{
		addSequential(new ResetEncoder());
		addSequential(new DriveStraight(target));
	}
}
