/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Encoder;
/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {
  private WPI_TalonSRX talon;
  private WPI_VictorSPX VictorSPX;
  private WPI_VictorSPX VictorSPX2;
  
  private WPI_TalonSRX talon2;
  private WPI_VictorSPX VictorSPX3;
  private WPI_VictorSPX VictorSPX4;
  boolean tank;

  private Encoder leftEncoder, rightEncoder;

 Joystick leftJoy, rightJoy;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Drivetrain(WPI_TalonSRX talon, WPI_TalonSRX talon2, WPI_VictorSPX VictorSPX, WPI_VictorSPX VictorSPX2, WPI_VictorSPX VictorSPX3, WPI_VictorSPX VictorSPX4, Encoder leftEncoder, Encoder rightEncoder) {
    this.talon = talon;
    this.VictorSPX = VictorSPX;
    this.VictorSPX2 = VictorSPX2;

    this.talon2 = talon2;
    this.VictorSPX3 = VictorSPX3;
    this.VictorSPX4 = VictorSPX4;

    this.leftEncoder = leftEncoder;
    this.rightEncoder = rightEncoder;

    tank = false;

    double pulseFraction = 1.0 / 256;
    double wheelDiameter = 5;
    leftEncoder.setDistancePerPulse(pulseFraction * Math.PI * wheelDiameter);
    rightEncoder.setDistancePerPulse(pulseFraction * Math.PI * wheelDiameter);
    leftEncoder.reset();
    rightEncoder.reset();
    rightEncoder.setReverseDirection(true);
  }


//Arcade Drive
  public void tankdrive(double leftspeed, double rightspeed) {
    talon.set(-leftspeed);
    VictorSPX.set(-leftspeed);
    VictorSPX2.set(-leftspeed);

    talon2.set(rightspeed);
    VictorSPX3.set(rightspeed);
    VictorSPX4.set(rightspeed);
  }



//Tank Drive
  public void arcadedrive(double xspeed, double zrotation) {
    talon.set(-xspeed + zrotation);
    VictorSPX.set(-xspeed + zrotation);
    VictorSPX2.set(-xspeed + zrotation);

    talon2.set(xspeed + zrotation);
    VictorSPX3.set(xspeed + zrotation);
    VictorSPX4.set(xspeed + zrotation);
  }
   
  public boolean tank(){
    return tank;
  }
  
  public void tankvalue(boolean tank){
    this.tank = tank;
  }

  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
