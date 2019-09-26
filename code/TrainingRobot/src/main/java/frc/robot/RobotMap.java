/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    static WPI_TalonSRX LTalon, RTalon;
    static WPI_VictorSPX LVictor1, LVictor2, RVictor1, RVictor2;

    static {
        LTalon = new WPI_TalonSRX(8);
        LVictor1 = new WPI_VictorSPX(9);
        LVictor2 = new WPI_VictorSPX(10);

        RTalon = new WPI_TalonSRX(5);
        RVictor1 = new WPI_VictorSPX(6);
        RVictor2 = new WPI_VictorSPX(7);
    }
}
