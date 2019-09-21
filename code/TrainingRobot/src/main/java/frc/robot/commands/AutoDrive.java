/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.commands.AutoTurn;
import frc.robot.subsystems.Drivetrain;

public class AutoDrive extends Command {
  private Drivetrain drivetrain;
  private Timer timer;
  private AutoTurn autoTurn;
  public AutoDrive(Drivetrain drivetrain) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(this.drivetrain = drivetrain);
    timer = new Timer();
    autoTurn = new AutoTurn(drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    drivetrain.run(0.5,0.5);
    timer.delay(1);
    end();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    autoTurn.start();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
