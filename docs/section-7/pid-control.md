# PID Control

## Introduction
How do you get the robot to move a certain distance? Certainly, you can experimentally find the amount of time and power you need to run to get it to a distance, but that is not going to work for a variable distance. As well, the robot would be subject to effects such as friction and would likely fall short. PID (Proportional Integral Derivative) control is a method of controlling a robot's movement based on its distance from the target, its speed, and the time elapsed. If tuned correctly, you can get pretty great results.

## Theory
PID control can work for adjusting position, velocity - basically any task that requires meeting some setpoint given a current value. From here onwards, we will be assuming that we are using PID control for adjusting position.

Parts of the PID equation require some calculus. It is OK if parts of the equation don't quite make sense - all that matters is a conceptual understanding. Without further ado, here is the PID equation:

\[u(t) = k_F + k_{P} \cdot e(t) + \displaystyle k_{I}\int_{t_0}^{t}e(t)dt + k_{D}\frac{d}{dt} e(t)\]

Now what does this all mean? Let's break it down into four terms: the proportional term, the integral term, the derivative term, and the feed-forward term. Also, let \(e\) represent the error between our current position and our setpoint, or \(e = \text{setpoint} - \text{position}\) and let \(u\) be the "adjustment" value - the amount we wish to apply to our motors.

### Proportional Term
Let's try to develop the PID equation from the ground-up. Where should we start? Well, we obviously want to utilize the current error in some way. What is a simple function we could use to get the adjustment from our current error? How about a simple multiplication?

\[u(t) = k_{P} \cdot e(t)\]

It may not look like it, but this simple formula does pretty well in practice. Let's try it out on a simple task: I want to move from 0 meters to 3 meters, I have motors that move the robot at a max speed of 3.5 m/s, and we use just the update function above with \(k_P = 0.5\), updating every 0.25 seconds. Assume that when I supply a percentage value to the motors that they move at the exact percentage of maximum speed (e.g: supplying 0.5 makes the robot move at exactly half-speed).

\[\text{New Position} = \text{Position} + \text{Update Rate} \cdot \text{Max Speed} \cdot \text{Update}\]

Time (s) | Position (m) | Update (capped at 1.0) | New Position (m)
-------- | ------------ | ---------------------- | ----------------
0 | 0 | 1.0 | 0.875
0.25 | 0.875 | 1.0 | 1.75
0.5 | 1.75 | 0.625 | 2.297
0.75 | 2.297 | 0.3515 | 2.604
1 | 2.604 | 0.198 | 2.778
1.25 | 2.778 | 0.111 | 2.875
1.5 | 2.875 | 0.0625 | 2.93

Not bad for a simple model. We could change \(k_P\) around or change how frequently we update to get faster convergence, but I think you get the idea. Note that, due to effects such as friction or internal motor resistance, small updates such as that at 1.5 seconds may barely move the robot at all. We will revist this idea when we cover the feed-forward term.

### Integral Term
Now let's make the equation more powerful. What if we were to act not just on our current error, but also our past error. That way, if the kP term is incrementing slowly, we can speed up the process a bit. One way of doing this is to just sum our past errors and add that to the equation.

\[u(t) = k_{P} \cdot e(t) + k_{I} \cdot \sum_{T = 0}^{T = t} e(T)\]

More technically speaking, if \(e(t)\) was a continuous function, we would want to find the area under the \(e(t)\) curve (sort of representing the total error) by taking the integral of the error.

\[u(t) = k_F + k_{P} \cdot e(t) + \displaystyle k_{I}\int_{t_0}^{t}e(t)dt\]

#### A Brief Calculus Aside

The integral of a function simply finds the area under the graph of that function, kind of like a record of how far we have travelled. But how do we find the area under a function? Well, consider the graph \(f(x) = x^2\). One way we can find the area under a curve is by sampling the function at various points and by drawing a rectangle with one vertex at the sampled point and an edge on the x-axis, as shown in the image below.

![x^2](https://ka-perseus-graphie.s3.amazonaws.com/2d9fe530575792f1a009163bbe9c221182ea2564.svg)

The area of each of these rectangles is \(f(x_i)\delta x\) where \(f(x_i)\) is the height of the ith rectangle and \(\delta x\) is the width of each rectangle, equal to our sampling rate. For example, if we sampled by 1 between \(x = 0\) and \(x = 4\), the area under the curve \(S\) is approximated by:

\[S \approx (1^2)(1) + (2^2)(1) + (3^2)(1) + (4^2)(1) = 30\]

If we were to choose a smaller sampling rate, such as 0.25:

\[S \approx (1^2)(0.25) + (1.25^2)(0.25) + (1.5^2)(0.25) + ... + (3.75^2)(0.25) + (4^2)(0.25) = 23.15625\]

In fact, if we make the sampling rate *infinitely small*, our summing rectangles method exactly equals \(S\). Try it out yourself with small sampling rates!

![Reimann Sum](https://upload.wikimedia.org/wikipedia/commons/6/61/Riemann_sum_%28rightbox%29.gif)

That is what an integral does. It sums an infinite number of rectangles with infinitely small widths. The way integrals are exactly calculated is beyond the scope of this section, but, if you are interested, use anti-derivatives.

#### Back to PID

In practice, when using the I term, we choose a very small sampling rate (usually every 0.02 seconds) and keep a trailing sum, as follows:

```
double dt = 0.02;
double integral = 0.0;

public void calculate() {
    double error = /* Your error code here */;
    // ...
    // I term calculation
    integral += error * dt
    // etc.
}
```

Depending on the application, an I term may not be necessary. However, if you do plan on using an I term, make sure that kI is small, else your adjustment value will grow *very* quickly.

Using the example from the previous section, let's include an I term with \(k_I = 0.02\).

Time (s) | Position (m) | Update (capped at 1.0) | New Position (m) | Error Sum
-------- | ------------ | ---------------------- | ---------------- | --------
0 | 0 | 1.0 | 0.875 | 0.75
0.25 | 0.875 | 1.0 | 1.75 | 1.28
0.5 | 1.75 | 0.65 | 2.319 | 1.5925
0.75 | 2.319 | 0.37 | 2.643 | 1.763
1 | 2.643 | 0.214 | 2.83 | 1.85
1.25 | 2.83 | 0.122 | 2.94 | 1.89

### Derivative Term
What if we wanted our model to predict what the *next* error might be. We are incorporating the present (P) and the past (I) so why not the future? This is where the derivative comes in.

\[u(t) = k_{P} \cdot e(t) + \displaystyle k_{I}\int_{t_0}^{t}e(t)dt + k_{D}\frac{d}{dt} e(t)\]

#### A Brief Calculus Aside

Think of the derivative as the rate of change of the function at a specific time, kind of like speed at which error is changing. Usually, when one finds the rate of change of a function, they find the rate of change between two points on the function and find the slope. The line connecting these two points with this slope is known as a secant line. If we want to know the rate of change of the function at a point, we need to find the line tangent at that point.

![Secant vs tangent](https://www.mathsisfun.com/geometry/images/secant-line.svg)

Like we did with the integral, we can estimate the rate of change at a point by finding the slope of secant lines with smaller and smaller differences in x-value. The secant lines will then seem to approach the tangent line. If the difference is infinitely small, then the slope of the secant line will equal the slope of the tangent line.

![Secant approaching tangent](https://www.maplesoft.com/view.aspx?SI=3473/secant1.gif)

#### Back to PID

Implementing the derivative term is actually quite simple. All we need is a record of what the previous error was and estimate the slope of the secant line between our last error and our current error.

```
double dt = 0.02;
double last_error = 0.0;

public void calculate() {
    double error = /* Your error code here */;
    // ...
    // The last error and error are dt seconds apart.
    double derivative += (error - last_error) / dt;
    // etc.
}
```

### Feed-Forward Term
And finally, we have reached the end: the feed-forward term. This one is very simple to implement. As mentioned in the section on the P term, sometimes supplying a small update value won't be enough to overcome forces pushing against motion, such as friction or [back-EMF](https://en.wikipedia.org/wiki/Counter-electromotive_force). So what do we do? Simply add a constant representing the minimum amount needed to cause the motor to move.

\[u(t) = k_F + k_{P} \cdot e(t) + \displaystyle k_{I}\int_{t_0}^{t}e(t)dt + k_{D}\frac{d}{dt} e(t)\]

Implementing this is code is also easy. Once the calculation is finished, add your kF term. 

## Implementation
Often, one does not need to implement PID Control from the ground-up. Many motor controllers have PID controllers built into the class. All that needs to be done is just to set the setpoint, kP, kI, kD, and kF (as well as some other configurations). For example, all [`BaseMotorControllers`](https://www.ctr-electronics.com/downloads/api/cpp/html/classctre_1_1phoenix_1_1motorcontrol_1_1can_1_1_base_motor_controller.html) in the CTRE_Phoenix API (think Talon_SRX and Victor_SPX) can do PID as follows:

```
// Config
motor.config_kP(0, kP);
motor.config_kI(0, kI);
motor.config_kD(0, kD);
motor.config_kF(0, kF);
// Tell the PID loop how close we want to get to the setpoint in sensor units
motor.configAllowableClosedloopError(0, closeness);
// Tell the PID loop which sensor to use. In this case use a quadrature motor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder)

// Move to 0 on the selected sensor
motor.set(ControlMode.Position, 0);
```

And for SparkMax controllers, we use the [`CANPIDController`](revrobotics.com/content/sw/max/sw-docs/java/com/revrobotics/CANPIDController.html):
```
CANPIDController pidController = motor.getPIDController();
// Config
pidController.setOutputRange(-1.0, 1.0);
pidController.setP(kP, 0);
pidController.setI(kI, 0);
pidController.setD(kD, 0);
pidController.setFF(kF, 0);
// Tell the PID loop how close we want to get to the setpoint in rotations
pidController.setSmartMotionAllowedClosedLoopError​(closeness, 0);

// Move to 0 on the quadrature encoder
pidController.setReference​(0.0, ControlType.kPosition, 0)
```

For general purposes, you can use the [`PIDController`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/controller/PIDController.html) class:
```
// No kF
PIDController pidController = new PIDController(kP, kI, kD);
pidController.setSetpoint(0);
// Tell the PID loop how close we want to get to the setpoint in rotations
pidController.setTolerance(closeness);

double state;
if (!pidController.atSetpoint()) {
    // getState() and update() are not actual functions. They are placeholders for your own code.
    // Get the current state of what is being controlled
    state = getState(); 
    // Do something with the calculated adjustment
    update(pidController.calculate(state));
}
pidController.reset();
```

It is highly recommended that you check out the documentation for each of these classes.

# Conclusion
Whew! That was a lot of information. Most of the math covered here is handled (thankfully) by WPILib, however, it is important to understand what is actually happening so that you can properly tune your control loops (it is also really cool).

But PID is just the beginning. There are many more complicated and more powerful control methods built upon PID that will be discussed later in this section, including Motion Profiling using Pathweaver and Drivetrain Characterization.

***

> **xkcd #689: FIRST Design**
> 
> ![FIRST](https://imgs.xkcd.com/comics/first_design.png)
> 
> You might use PID to control the elevator.
>
> _<https://xkcd.com/689/>_