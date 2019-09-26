/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.LeftMotors;
import frc.robot.subsystems.RightMotors;
import edu.wpi.first.wpilibj.Joystick;

public class RunDrivetrainArcade extends Command {
  private LeftMotors leftMotors;
  private RightMotors rightMotors;
  private Joystick joystick1, joystick2;
  public RunDrivetrainArcade(Joystick joystick1, Joystick joystick2, LeftMotors leftMotors, RightMotors rightMotors) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(this.leftMotors = leftMotors);
    requires(this.rightMotors = rightMotors);
    this.joystick1 = joystick1;
    this.joystick2 = joystick2;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    leftMotors.run(joystick1.getY()+joystick2.getX());
    rightMotors.run(joystick1.getY()-joystick2.getX());

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
