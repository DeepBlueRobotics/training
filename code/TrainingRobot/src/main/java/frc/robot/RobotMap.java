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
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    static WPI_TalonSRX talonL;
    static WPI_TalonSRX talonL2;
    static WPI_VictorSPX victorSPXL;
    static WPI_TalonSRX talonR;
    static WPI_TalonSRX talonR2;
    static WPI_VictorSPX victorSPXR;
    static Encoder autoEnc;
    static Joystick leftJoy;
    static Joystick rightJoy;
    static Joystick controller;

    static {
        talonL = new WPI_TalonSRX(8);
        talonL2 = new WPI_TalonSRX(9);
        victorSPXL = new WPI_VictorSPX(10);
        talonR = new WPI_TalonSRX(5);
        talonR2 = new WPI_TalonSRX(6);
        victorSPXR = new WPI_VictorSPX(7);
        autoEnc = new Encoder(new DigitalInput(2),new DigitalInput(3)); //TODO: get encoder ports
        autoEnc.setDistancePerPulse(5.0/256 * Math.PI); //TODO: get actual value for this
        leftJoy = new Joystick(0);
        rightJoy = new Joystick(1);
        controller = new Joystick(2);
    }
}
