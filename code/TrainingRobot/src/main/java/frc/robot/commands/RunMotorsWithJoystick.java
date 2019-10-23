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

    //This system/math decides how much power to give to each set of motors
    //Its really dumb but I think it will work pretty cool
    //It favors going straight over turning
    //so if you have max turn and max forward
    //it will do 2/3 of going max forward and 1/3 turning
    //Also adam said i dont need to import math, if it doesn't work blame him lol
    double push=this.lJoy.getY();
    double turn=this.rJoy.getX();
    double weight=Math.abs(push)+Math.abs(turn)+0.00001;
    if (weight<1.0)
    {
      weight=1;
    }
    double left=(push-turn)/weight;
    double right=(push+turn)/weight;
    
    motors.run(left,right);
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
