package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SwitchMode extends InstantCommand {
  
  public SwitchMode() {
    
  }
  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  SmartDashboard.put("yeet",1-SmartDashboard.get("yeet",0));
  }

  }

 


