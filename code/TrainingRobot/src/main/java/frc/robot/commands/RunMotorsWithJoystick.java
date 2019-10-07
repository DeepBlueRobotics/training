package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Motors;
import edu.wpi.first.wpilibj.Joystick;

public class RunMotor extends Command {
  private Motors motors;
  private Joystick joystick;
  public RunMotor(Motors motors,Joystick joystick) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(this.motors = motors);
    requires(this.joystick=joystick)
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    motors.run(this.joystick.getY());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    motors.run(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
