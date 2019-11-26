/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.DigitalInput;



/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    static WPI_TalonSRX talon;
    static WPI_VictorSPX VictorSPX;
    static WPI_VictorSPX VictorSPX2;
    static WPI_TalonSRX talon2;
    static WPI_VictorSPX VictorSPX3;
    static WPI_VictorSPX VictorSPX4;
    static Encoder leftEncoder;
    static Encoder rightEncoder;
    static {
        talon = new WPI_TalonSRX(8);
        VictorSPX = new WPI_VictorSPX(9);
        VictorSPX2 = new WPI_VictorSPX(10);
        talon2 = new WPI_TalonSRX(5);
        VictorSPX3 = new WPI_VictorSPX(6);
        VictorSPX4 = new WPI_VictorSPX(7);

        leftEncoder = new Encoder(new DigitalInput(0), new DigitalInput(1));
        rightEncoder = new Encoder(new DigitalInput(2), new DigitalInput(3));

    }
}
