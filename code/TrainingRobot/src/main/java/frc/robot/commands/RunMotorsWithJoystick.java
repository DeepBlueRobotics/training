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
public class RunMotorsWithJoystick extends Command {
  private Drivetrain drivetrain;
  private Joystick leftJoy;
  private Joystick rightJoy;
  public RunMotorsWithJoystick(Drivetrain drivetrain, Joystick leftJoy, Joystick rightJoy) {
    requires(drivetrain);
    this.drivetrain = drivetrain;
    this.leftJoy = leftJoy;
    this.rightJoy = rightJoy;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }
  

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(drivetrain.tank()){
      drivetrain.tankdrive(leftJoy.getY(), rightJoy.getY());
    } else{
      drivetrain.arcadedrive(leftJoy.getY(), rightJoy.getX());
    }
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
