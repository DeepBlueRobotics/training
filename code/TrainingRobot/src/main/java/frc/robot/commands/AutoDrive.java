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

public class AutoDrive extends Command {
  private Drivetrain drivetrain;
  private Encoder encoder;
  private double distance;

  public AutoDrive(Drivetrain drivetrain, double distance, Encoder encoder) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(this.drivetrain = drivetrain);
    this.encoder = encoder;
    this.distance = distance;

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    encoder.reset();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    drivetrain.run(0.2,0.2);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return encoder.getDistance() <= -distance;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    drivetrain.run(0,0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
