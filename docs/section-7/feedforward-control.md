<script
  src="https://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"
  type="text/javascript">
</script>

Feedforward control means providing the mechanism with the control signal you think it needs to make the mechanism do what you want, without any knowledge of where the mechanism currently is. If you ever used a joystick to "directly" control the speed of a motor through applied voltage, maybe like this:

```java
Joystick stick = new Joystick(0);
public void robotPeriodic() {
    motor.set(stick.getY());
}
```
That is feedforward control! You are giving the motor the amount of voltage needed to move at the desired speed which is determined by how you move the joystick. 

Now, if you wanted to have the motor to move at a certain velocity rather than a percentage of full power like the case shown above, you will need to determine a mathematical model that can calculate the voltage needed to move at a certain velocity.

Feedforward isn't limited to controlling velocity, you can determine the voltage necessary to drive a motor at a certain velocity, acceleration, current, and many other factors. And it doesn't have to be voltage, it can be the air pressure released by a pneumatic actuator. And it isn't limited to just motors, feedforward applies to an entire arm subsystem, drivetrain, and many other mechanisms, but only if you can determine an accurate model for these mechanisms.

We will first learn about the most common feedforward model used for motors, then show how the model can be used to control motor velocity and acceleration. Afterwards, we will cover more complicated mechanisms such as the arm subsystem.

## The Permanent-Magnet DC Motor Feedforward Equation
[Click here to read about the equation](https://docs.wpilib.org/en/stable/docs/software/advanced-controls/introduction/introduction-to-feedforward.html).

For those that only want a quick summary. Here is the equation:

![Motor Feedforward Equation](motor_feedforward_equation.PNG)

where `V` is the applied voltage, `d` is the displacement (position) of the motor, `d` with a single dot is its velocity, and `d` with a double dot is its acceleration (the “overdot” notation traditionally denotes the derivative with respect to time). `kS`, `kV`, and `kA` are all constants that are tuned.

- The `kS` term (including the `sgn(d)` part) is the amount of voltage needed to overcome the motor's static friction, or in other words to just barely get it moving.
- The `kV` term (including the `d` dot part) is the amount of voltage needed to hold the motor at a given constant velocity.
- The `kA` term (incluidng the `d` double dot part) is the amount of voltage needed to drive the motor at a given constant acceleration.

When you add up all these values which equals `V`, that is voltage needed to keep a motor at velocity (`d` with dot) and acceleration (`d` with two dots).

Then, to drive the motor at the desired velocity and acceleration, it is as easy as writing:
```java
// assume kS, kV, and kA are defined
double vel = 5;
double accel = 1;
double feedforwardVolts = kS * Math.signum(vel) + kV * vel + kA * accel;
motor.setVoltage(feedforwardVolts);
```
!!! warning
    The amount of voltage calculated is the amount of voltage used to **MAINTAIN** the motor at the specified velocity and acceleration. When `motor.setVoltage(feedforwardVolts)` is run, that does not automatically drive the motor to the specified velocity and acceleration. If the code was run when the motor is at rest, then the voltage will be used the overcome the static friction and accelerate the motor, not to maintain the motor at the specified velocity and acceleration as the motor is not at the specified velocity.

!!! warning
    The code excerpt is only meant to show how feedforward works. This is not how we actually implement feedforward, but should give you a better idea of the inner workings of feedforward.

In addition, feedforward can also be used for elevators and arms. There is one additional constant `kG` which is used to counteract the force of gravity.

## Tuning and System Idenfication
Similar to PID, you can tune values by manually guessing and checking.

[Click here to try tuning a feedforward controller. SCROLL TO "PURE FEEDFORWARD CONTROL", SKIP EVERYTHING ELSE](https://docs.wpilib.org/en/stable/docs/software/advanced-controls/introduction/tuning-flywheel.html)

Follow the instructions and see if you can get the optimal tuning solution. The model simulates a flywheel shooter mechanism and halfway through the simulation it shoots a ball. **DO NOT SKIP THIS PRACTICE**

While manual tuning works, WPILIB provides a way to generate `kS`, `kV`, and `kA`, called System Identification, or SysID for short.

!!! warning
    Do not move on if you don't know how [lambdas/consumers](https://docs.wpilib.org/en/stable/docs/software/basic-programming/functions-as-data.html) work and the [Java Unit library](https://docs.wpilib.org/en/stable/docs/software/basic-programming/java-units.html).

System Identification is the process of determining a mathematical model for the behavior of a system through statistical anaylsis of its inputs and outputs. SysID has a process to determine `kS`, `kV`, and `kA` for the motor, so you don't have to do any tuning! They also provide PID values, but treat them as a "starting point" for further tuning.

Read the following WPILIB articles:

- [Creating an Identification Routine](https://docs.wpilib.org/en/stable/docs/software/advanced-controls/system-identification/creating-routine.html)
- [Running the Identification Routine](https://docs.wpilib.org/en/stable/docs/software/advanced-controls/system-identification/running-routine.html)

<hr>

For those that want a quick summary:

The SysID tool runs two types of tests: 

- Quasistatic: mechanism is gradually sped up (determines amount of voltage needed for velocity)
- Dynamic: mechanism is given a constant 'step voltage' (determines amount of voltage needed for acceleration)

These tests can be run backwards and forwards. To create the test, you have to write create a SysIdRoutine object:
```java
public class Shooter extends SubsystemBase {

    private final CANSparkMax motor = 
        MotorControllerFactory.createSparkMax(0, MotorConfig.NEO);
    
    private final SysIdRoutine sysIdRoutine = 
        new SysIdRoutine(
            new SysIdRoutine.Config(),
            new SysIdRoutine.Mechanism(
                this::voltageDrive, 
                this::logMotors, 
                this
            )
        );
}
```

The `SysIdRoutine.Config()` is where you specify custom parameters for the quasistatic and dynamic tests.
For example if you write:
```java
// ramp rate of 3 volts per second and step voltage of 8 volts
new SysIdRoutine.Config(3, 8); 
```
By default the ramp rate is 1 volt per second and the step voltage is 7 volts. The reason why you may want to lower the ramp rate is so it doesn't run too fast and smash into the wall. Typically the default works fine.
The `Config` object also accepts a timeout and callback (function that is called when the test is over).

Now that you have set the parameters, you need to specify which motors receive voltage and how you will log the data for analysis. In this example we will be controlling a single motor. The `Mechanism` object accepts those two functions:
```java
new SysIdRoutine.Mechanism(this::driveMotor, this::logMotor, this)
```

Here are the two functions that drive and log the motor voltage:
```java
// Mutable holder for unit-safe voltage values, persisted to avoid reallocation.
private final MutableMeasure<Voltage> voltage = mutable(Volts.of(0));

public void driveMotor(Measure<Voltage> volts) {
    motor.setVoltage(volts.in(Volts));
}

public void logMotor(SysIdRoutineLog log) {
    log.motor("shooter-motor")
        .voltage(voltage.mut_replace(
            motor.get() * RobotController.getBatteryVoltage(), 
            Volts
        ));
}
```
Note that [SysIdRoutineLog](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/wpilibj/sysid/SysIdRoutineLog.html) has a handy `motor` method which returns a `SysIdRoutineLog.MotorLog` object used to log voltage, linear position, velocity, and more. [Take a look at all the properties the object logs](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/wpilibj/sysid/SysIdRoutineLog.MotorLog.html).

You may also notice that the only values that the logger logs are of instances that must be of `MutableMeasure<(insert measure)>`. This records the values along with its units. You can't just log a value.

!!! note
    Notice that you can write anything in the `driveMotor()` and `logMotor()` methods. You are not limited to only powering a single motor but can power an entire elevator, arm, etc. SysID also analyzes elevators and arms which calculate the `kG` constant.

After you set up the testing parameters and mechanism to test, the SysIdRoutine provides functions that return a command to run the test.

```java
public Command sysIdQuasistatic(SysIdRoutine.Direction direction) {
  return routine.quasistatic(direction);
}

public Command sysIdDynamic(SysIdRoutine.Direction direction) {
  return routine.dynamic(direction);
}
```

Typically it is recommended to bind these commands to controller buttons or an autonomous routine. If using a controller, it is recommended to bind them like this:

```java
GenericHID controller = new GenericHID(0);
Shooter shooter = new Shooter();

new JoystickButton(controller, Button.kY.value)
    .whileTrue(
        shooter.sysIdQuasistatic(SysIdRoutine.Direction.kForward)
    );
```
Now you are ready to enable and run the tests! Typically the longer you run them, the more data you get which will lead to more accurate calculations. After all four tests have been run, use the [DataLogTool](https://docs.wpilib.org/en/stable/docs/software/telemetry/datalog-download.html) to get the files.

Afterwards, put them into the SysID tool which can be opened by `Ctrl + Shift + P` and typing `Start Tool`. Here are the following articles for analyzing the data and determining the constants:

- [Loading Data](https://docs.wpilib.org/en/stable/docs/software/advanced-controls/system-identification/loading-data.html)
- [Viewing Diagnostics](https://docs.wpilib.org/en/stable/docs/software/advanced-controls/system-identification/viewing-diagnostics.html)
- [Analyzing Data](https://docs.wpilib.org/en/stable/docs/software/advanced-controls/system-identification/analyzing-gains.html)
- [Additional Utilities and Tools](https://docs.wpilib.org/en/stable/docs/software/advanced-controls/system-identification/additional-utils.html)

Once you have gotten good data and analysis, you should obtain `kS`, `kV`, `kA` and PID constants.

!!! warning
    The PID constants are only a starting point and should be tuned more.

## Implementation

WPILIB provides a [SimpleMotorFeedforward](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/math/controller/SimpleMotorFeedforward.html) class that runs feedforward for a motor. After you obtain your feedforward constants (`kS`, `kV`, `kA`) from SysID, you put them into the constructor of the `SimpleMotorFeedforward` and use the listed methods.
```java
// Create a new SimpleMotorFeedforward with gains kS, kV, and kA
SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(kS, kV, kA);

// Calculates the feedforward for a velocity of 10 units/second and an 
// acceleration of 20 units/second^2
// Units are determined by the units of the gains passed in at construction.
double volts = feedforward.calculate(10, 20);

// drives the motor to the desired velocity and acceleration calculated 
// from the feedforward controller
motor.setVoltage(volts);
```

That's it!
Similarly WPILIB provides a `ArmFeedforward` and an `ElevatorFeedforward` class whose only difference from `SimpleFeedforward` is that it accepts a `kG` value.

## Combining Feedforward and Feedback Control

[Read WPILIB's article](https://docs.wpilib.org/en/stable/docs/software/advanced-controls/controllers/combining-feedforward-feedback.html)
(It is as easy as just adding them together)
```java
// Adds a feedforward to the loop output before sending it to the motor
motor.setVoltage(pid.calculate(encoder.getDistance(), setpoint) + feedforward);
```

## Conclusion

Using Feedforward and Feedback, you should now be able to control your mechanisms to go at desired velocities and accelerations. 

!!! question
    However, what if you want your motor to turn to a specific position? (Hint: read next section on trapezoidal control)

## Extra: Swim Shady's (2023 Charged Up) Double Jointed Arm

!!! warning
    Requires a high level understanding of feedforward + feedback, trapezoidal control, and physics (moment of inertia, torque, etc)

[Here is a google doc that explains the math/physics behind creating and characterizing (running special System Idenfication procedures) a double jointed arm](https://docs.google.com/document/d/1I1YYVotjAXqaMdx4E6DjC-nBDbp6SSmooaWYvMjVmsA/edit?usp=sharing). Due to the complexity of having another joint attached to an already rotating arm, the feedforward calculations done by System Idenfication and other tools was not enough and we needed to do our own math.