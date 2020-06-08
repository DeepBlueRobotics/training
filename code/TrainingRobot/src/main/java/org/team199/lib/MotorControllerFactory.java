package org.team199.lib;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class MotorControllerFactory {
    public static WPI_TalonSRX createTalon(int id) {
        WPI_TalonSRX talon = new WPI_TalonSRX(id);
    
        // Put all configurations for the talon motor controllers in here.
        talon.enableCurrentLimit(true);
        talon.setNeutralMode(NeutralMode.Brake);
    
        return talon;
    }

    public static WPI_VictorSPX createVictor(int port) {
        WPI_VictorSPX victor = new WPI_VictorSPX(port);
    
        // Put all configurations for the victor motor controllers in here.
        victor.setNeutralMode(NeutralMode.Brake);
    
        return victor;
    }
}