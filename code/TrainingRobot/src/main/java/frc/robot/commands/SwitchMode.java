package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SwitchMode extends InstantCommand {
  
  public SwitchMode() {
    
  }
  // Called just before this Command runs the first time
  @Override
  protected void execute() {
  SmartDashboard.putNumber("TeleopMode",1-SmartDashboard.getNumber("TeleopMode",0));
  }
  @Override
  protected boolean isFinished(){
    return true;
  }

  }

 


