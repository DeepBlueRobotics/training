/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Encoder;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    static WPI_TalonSRX LTalon1, RTalon1;
    static WPI_VictorSPX  LTalon2, RTalon2, LVictor1, RVictor1;
    static Encoder LEncoder, REncoder;

    static {
        LTalon1 = new WPI_TalonSRX(8);
        LTalon2 = new WPI_VictorSPX(9);
        LVictor1 = new WPI_VictorSPX(10);

        RTalon1 = new WPI_TalonSRX(5);
        RTalon2 = new WPI_VictorSPX(6);
        RVictor1 = new WPI_VictorSPX(7);

        LEncoder = new Encoder(0, 1);
        REncoder = new Encoder(2, 3);
    }
}
