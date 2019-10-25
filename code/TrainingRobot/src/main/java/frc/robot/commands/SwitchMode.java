package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SwitchMode extends InstantCommand {
  
  public SwitchMode() {
    
  }
  // Called just before this Command runs the first time
  @Override
  protected void execute() {
  SmartDashboard.putNumber("yeet",1-SmartDashboard.getNumber("yeet",0));
  }
  @Override
  protected boolean isFinished(){
    return true;
  }

  }

 


