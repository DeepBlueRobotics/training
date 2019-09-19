/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Drivetrain;

public class Drive extends Command {
  private Drivetrain drivetrain;
  private Joystick leftJoy;
  private Joystick rightJoy;

  public Drive(Drivetrain drivetrain, Joystick leftJoy, Joystick rightJoy) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(this.drivetrain = drivetrain);
    this.leftJoy = leftJoy;
    this.rightJoy = rightJoy; 
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    drivetrain.run(leftJoy, rightJoy);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    drivetrain.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
