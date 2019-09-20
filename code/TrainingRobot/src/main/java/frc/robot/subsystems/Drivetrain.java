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
  private WPI_VictorSPX victor1Left;
  private WPI_VictorSPX victor2Left;
  private WPI_TalonSRX talonRight;
  private WPI_VictorSPX victor1Right;
  private WPI_VictorSPX victor2Right;
  private boolean isArcade;

  public Drivetrain(WPI_TalonSRX talonLeft, WPI_VictorSPX victor1Left, WPI_VictorSPX victor2Left,
                    WPI_TalonSRX talonRight, WPI_VictorSPX victor1Right, WPI_VictorSPX victor2Right) {
    this.talonLeft = talonLeft;
    this.victor1Left = victor1Left;
    this.victor2Left = victor2Left;
    this.talonRight = talonRight;
    this.victor1Right = victor1Right;
    this.victor2Right = victor2Right;
    this.isArcade = true;
  }

  public void swapMode() {
    isArcade = !isArcade;
  }

  public void run(Joystick leftJoy, Joystick rightJoy) {
    if (isArcade) {
      double speed = leftJoy.getY();
      double rotation = rightJoy.getX();
      double speedDiff = 0.1 * rotation;
      talonLeft.set(speed + speedDiff);
      victor1Left.set(speed + speedDiff);
      victor2Left.set(speed + speedDiff);
      talonRight.set(-speed + speedDiff);
      victor1Right.set(-speed + speedDiff);
      victor2Right.set(-speed + speedDiff);
    } else {
      double speedLeft = leftJoy.getY();
      double speedRight = rightJoy.getY();
      talonLeft.set(speedLeft);
      victor1Left.set(speedLeft);
      victor2Left.set(speedLeft);
      talonRight.set(-speedRight);
      victor1Right.set(-speedRight);
      victor2Right.set(-speedRight);
    }
  }

  public void stop() {
    talonLeft.set(0);
    victor1Left.set(0);
    victor2Left.set(0);
    talonRight.set(0);
    victor1Right.set(0);
    victor2Right.set(0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
