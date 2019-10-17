package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Motors;
import edu.wpi.first.wpilibj.Joystick;

public class RunMotorsWithJoystick extends Command {
  private Motors motors;
  private Joystick joystick;
  public RunMotorsWithJoystick(Motors motors,Joystick lJoy,Joystick rJoy) {
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
    
    //0 is arcade mode, 1 is tank

    if (SmartDashboard.getNumber("yeet",0)==0){arcadeDrive();}
    else
    {
      tankDrive();
    }
    
  }

  private void arcadeDrive() {
    double y=this.lJoy.getY();
    double x=this.rJoy.getX();
    double left=y+x;
    double right=y-x;
    
    
    
//this part has big boi errors
    motors.run(y+x,y-x);
  }

   private void tankDrive(){
     double l=this.lJoy.getY();
     double r=this.rJoy.getY();
     motors.run(l,r)

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
