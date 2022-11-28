How do you get the robot to move a certain distance? You could try to experimentally find the amount of time and power you need to run to get it to a distance, but that is not going to work for a variable distance or if there is a change in the environmental conditions such as the amount of friction. PID (Proportional Integral Derivative) control is a method, that, if tuned correctly, can yield pretty great results.

## Theory
Parts of the PID equation require some calculus. It is OK if parts of the equation don't quite make sense - all that matters is a conceptual understanding of what each term contributes. If you want a quick overview of the calculus topics used in the equation, you can check out [this](https://www.mathsisfun.com/calculus/derivatives-introduction.html) introduction to derivatives and [this](https://www.mathsisfun.com/calculus/integration-introduction.html) introduction to integrals.

WPIlib has two resources for learning PID. The first is an [introduction to control systems](https://docs.wpilib.org/en/stable/docs/software/advanced-control/introduction/control-system-basics.html) and the other is an [introduction to PID](https://docs.wpilib.org/en/stable/docs/software/advanced-control/introduction/introduction-to-pid.html). It is highly recommended that you read the documentation first before continuing.

If you are using PID for a velocity closed-loop or a current closed-loop, you might want to use a Velocity Feed Forward gain (\(k_F\)). For a detailed description of this constant, see [here](https://phoenix-documentation.readthedocs.io/en/latest/ch16_ClosedLoop.html?highlight=kf#calculating-velocity-feed-forward-gain-kf). 

## Implementation
Often, one does not need to implement PID Control from the ground-up. Many motor controllers have PID controllers built into the class. All that needs to be done is set the setpoint, kP, kI, and kD (as well as other configurations that you may want). For example, all [`BaseMotorControllers`](https://www.ctr-electronics.com/downloads/api/cpp/html classctre_1_1phoenix_1_1motorcontrol_1_1can_1_1_base_motor_controller.html) in the CTRE Phoenix API (Talon_SRX and Victor_SPX) can do PID as follows:

``` Java
// Config
motor.config_kP(0, kP);
motor.config_kI(0, kI);
motor.config_kD(0, kD);
// Tell the PID loop how close we want to get to the setpoint in sensor units
motor.configAllowableClosedloopError(0, closeness);
// Tell the PID loop which sensor to use. In this case use a quadrature encoder 
motor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
// Move to 0 on the selected sensor
motor.set(ControlMode.Position, 0);
```

And for SparkMax controllers, we use the [`CANPIDController`](https://revrobotics.com/content/sw/max/sw-docs/java/com/revrobotics/CANPIDController.html):
``` Java
CANPIDController pidController = motor.getPIDController();
// Config
pidController.setOutputRange(-1.0, 1.0);
pidController.setP(kP, 0);
pidController.setI(kI, 0);
pidController.setD(kD, 0);
// Tell the PID loop how close we want to get to the setpoint in rotations
pidController.setSmartMotionAllowedClosedLoopError​(closeness, 0);

// Move to 0 on the quadrature encoder
pidController.setReference​(0.0, ControlType.kPosition, 0)
```

For general purposes, you can use the [`PIDController`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/controller/PIDController.html) class:
``` Java
PIDController pidController = new PIDController(kP, kI, kD);
pidController.setSetpoint(0);
// Tell the PID loop how close we want to get to the setpoint in rotations
pidController.setTolerance(closeness);

// This code is assumed to be in a method or command called frequently.
double state;
if (!pidController.atSetpoint()) {
    // getError() and update() are not actual functions. They are placeholders for your own code.
    // Get the current state of what is being controlled
    error = getError(); 
    // Do something with the adjustment
    update(pidController.calculate(error));
}
// Only use this line once the error is "close enough"
pidController.reset();
```

You should check out the documentation for each of these classes.

# Conclusion
Thankfully, most of the math is handled by WPIlib or motor controller firmware. However, it is important to understand what is actually happening so that you can properly tune your control loops.

But PID is just the beginning. There are many more complicated and more powerful control methods built upon PID that will be discussed later in this section, including Motion Profiling using Pathweaver and Drivetrain Characterization.

***

> **xkcd #689: FIRST Design**
> 
> ![FIRST](https://imgs.xkcd.com/comics/first_design.png)
> 
> You might use PID to control the elevator.
>
> _<https://xkcd.com/689/>_