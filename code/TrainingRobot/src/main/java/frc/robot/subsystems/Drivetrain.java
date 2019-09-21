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

/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {

  private WPI_TalonSRX talonLeft;
  private WPI_TalonSRX talon2Left;
  private WPI_VictorSPX victorLeft;
  private WPI_TalonSRX talonRight;
  private WPI_TalonSRX talon2Right;
  private WPI_VictorSPX victorRight;
  private boolean isArcade;

  public Drivetrain(WPI_TalonSRX talonLeft, WPI_TalonSRX talon2Left, WPI_VictorSPX victorLeft,
                    WPI_TalonSRX talonRight, WPI_TalonSRX talon2Right, WPI_VictorSPX victorRight) {
    this.talonLeft = talonLeft;
    this.talon2Left = talon2Left;
    this.victorLeft = victorLeft;
    this.talonRight = talonRight;
    this.talon2Right = talon2Right;
    this.victorRight = victorRight;
    this.isArcade = true;
  }

  public void swapMode() {
    isArcade = !isArcade;
  }

  public void run(Joystick leftJoy, Joystick rightJoy) {
    if (isArcade) {
      double speed = -leftJoy.getY();
      double rotation = rightJoy.getX();
      double speedDiff = rotation;
      talonLeft.set(speed + speedDiff);
      talon2Left.set(speed + speedDiff);
      victorLeft.set(speed + speedDiff);
      talonRight.set(-speed + speedDiff);
      talon2Right.set(-speed + speedDiff);
      victorRight.set(-speed + speedDiff);
    } else {
      double speedLeft = -leftJoy.getY();
      double speedRight = -rightJoy.getY();
      talonLeft.set(speedLeft);
      talon2Left.set(speedLeft);
      victorLeft.set(speedLeft);
      talonRight.set(-speedRight);
      talon2Right.set(-speedRight);
      victorRight.set(-speedRight);
    }
  }

  public void run(double speedLeft, double speedRight)
  {
    talonLeft.set(speedLeft);
    talon2Left.set(speedLeft);
    victorLeft.set(speedLeft);
    talonRight.set(-speedRight);
    talon2Right.set(-speedRight);
    victorRight.set(-speedRight);
  }

  public void stop() {
    talonLeft.set(0);
    talon2Left.set(0);
    victorLeft.set(0);
    talonRight.set(0);
    talon2Right.set(0);
    victorRight.set(0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
