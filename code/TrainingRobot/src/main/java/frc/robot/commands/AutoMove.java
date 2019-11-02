package frc.robot.commands;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.subsystems.Motors;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoMove extends Command {
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
    if (lEncoder.getDistance()>2 || rEncoder.getDistance()>=2){
        SmartDashboard.putString("AutoMode","turn");
    }
    if (SmartDashboard.getString("AutoMode","turn").equals("forward"))
    {
      motors.run(1,1);
    }
    else if (SmartDashboard.getString("AutoMode","turn").equals("turn")){
      motors.run(1,-1);
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
