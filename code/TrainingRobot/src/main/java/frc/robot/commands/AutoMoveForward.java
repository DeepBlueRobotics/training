/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Encoder;

public class AutoMoveForward extends Command {
  private Encoder leftEncoder;
  private Encoder rightEncoder;
  private Drivetrain drivetrain;

  public AutoMoveForward(Encoder leftEncoder, Encoder rightEncoder, Drivetrain drivetrain) {
    this.leftEncoder = leftEncoder;
    this.rightEncoder = rightEncoder;
    this.drivetrain = drivetrain;
    double pulseFraction = 1.0 / 256;
    double wheelDiameter = 5;
    leftEncoder.setDistancePerPulse(pulseFraction * Math.PI * wheelDiameter);
    rightEncoder.setDistancePerPulse(pulseFraction * Math.PI * wheelDiameter);
    rightEncoder.setReverseDirection(true);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  leftEncoder.reset();
  rightEncoder.reset();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    drivetrain.tankdrive(1, 1);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (leftEncoder.getDistance() > 24 || rightEncoder.getDistance() > 24); 
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}

