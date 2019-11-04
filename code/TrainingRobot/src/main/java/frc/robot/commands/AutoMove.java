package frc.robot.commands;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.subsystems.Motors;

public class AutoMove extends CommandGroup {
  private Motors motors;
  private Encoder lEncoder;
  private Encoder rEncoder;
  
  public AutoMove(Motors motors,Encoder lEncoder,Encoder rEncoder) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(this.motors = motors);
    this.lEncoder=lEncoder;
    this.rEncoder=rEncoder;

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    addSequential(new Forward(motors));
    if (lEncoder.getDistance()>2 || rEncoder.getDistance()>=2)
    {
      addParallel(new TurnRight(motors));
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
    motors.run(0,0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
