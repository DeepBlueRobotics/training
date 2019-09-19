/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.Joystick;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    static WPI_TalonSRX talonL;
    static WPI_VictorSPX victorSPX1L;
    static WPI_VictorSPX victorSPX2L;
    static WPI_TalonSRX talonR;
    static WPI_VictorSPX victorSPX1R;
    static WPI_VictorSPX victorSPX2R;
    static Joystick leftJoy;
    static Joystick rightJoy;
    static Joystick controller;

    static {
        talonL = new WPI_TalonSRX(1);
        victorSPX1L = new WPI_VictorSPX(2);
        victorSPX2L = new WPI_VictorSPX(3);
        talonR = new WPI_TalonSRX(4);
        victorSPX1R = new WPI_VictorSPX(5);
        victorSPX2R = new WPI_VictorSPX(6);
        leftJoy = new Joystick(0);
        rightJoy = new Joystick(1);
        controller = new Joystick(2);
    }
}
