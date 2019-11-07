/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Joystick;

public class RunDrivetrain extends Command {
  private static boolean tankMode;
  private Drivetrain dt;
  private Joystick joystick1, joystick2;
  public RunDrivetrain(Joystick joystick1, Joystick joystick2, Drivetrain dt) {
    requires(this.dt = dt);
    this.joystick1 = joystick1;
    this.joystick2 = joystick2; 
    tankMode = false;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (tankMode)
      dt.run(joystick1.getY(), joystick2.getY());
    else 
      dt.run(joystick1.getY()-joystick2.getX(), joystick1.getY()+joystick2.getX());
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

  // Called in code to switch between arcade and tank mode
  public void switchMode() {
    tankMode = !tankMode;
  }
}