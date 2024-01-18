<script
  src="https://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"
  type="text/javascript">
</script>

## Feedforward Control

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
[Click here to read about the equation](https://docs.wpilib.org/en/stable/docs/software/advanced-controls/introduction/introduction-to-feedforward.html). Don't read past the "Variants of the Feedforward Equation".

For those that only want a quick summary. Here is the equation:

$$ V = K_{s} \cdot sgn(\dot{d}) + K_{v} \cdot \dot{d} + K_{a} \cdot \ddot{d} $$

where \\(V\\) is the applied voltage, \\(d\\) is the displacement (position) of the motor, \\(\dot{d}\\) is its velocity, and \\(\ddot{d}\\) is its acceleration (the “overdot” notation traditionally denotes the derivative with respect to time). \\(K_{s}\\), \\(K_{v}\\), and \\(K_{a}\\) are all constants that are tuned.

- \\(k_{s} \cdot sgn(\dot{d})\\) is the amount of voltage needed to overcome the motor's static friction, or in other words to just barely get it moving.
- \\(k_{v} \cdot \dot{d}\\) is the amount of voltage needed to hold the motor at a given constant velocity.
- \\(k_{a} \cdot \ddot{d}\\) is the amount of voltage needed to drive the motor at a given constant acceleration.

When you add up all these values which equals \\(V\\), that is voltage needed to keep a motor at velocity \\(\dot{d}\\) and acceleration \\(\ddot{d}\\).

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

## System Idenfication

## Implementation

## Conclusion