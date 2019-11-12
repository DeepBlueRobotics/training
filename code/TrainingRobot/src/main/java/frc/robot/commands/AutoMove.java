package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Motors;

public class AutoMove extends CommandGroup {
  private Motors motors;
  
  public AutoMove(Motors motors) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(this.motors = motors);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //SmartDashboard.putNumber("Gets to forward",0);
    SmartDashboard.putNumber("Gets to execute",1);
    if (SmartDashboard.getNumber("lEncoder distance",0)>=24 || SmartDashboard.getNumber("rEncoder distance",0)>=24)
    {
      addSequential(new TurnRight(motors));
      
    }
    else{
      SmartDashboard.putNumber("Gets to forward",1);
      addSequential(new Forward(motors));
      //motors.run(0.5,0.5);
      SmartDashboard.putNumber("Gets to forward",2);
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
    //SmartDashboard.putNumber("It's probably broken",123345);
    motors.run(0,0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
