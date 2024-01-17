How do you get the robot to move a certain distance? You could try to experimentally find the amount of time and power you need to run to get it to a distance, but that is not going to work for a variable distance or if there is a change in the environmental conditions such as the amount of friction.

How do you get a flywheel to move at a certain velocity? There is no `.setVelocity()` method that allows you to easily control a flywheel.

How do you get an arm to move to a certain position? Again, there is no `.setPosition()` method that can accurately supply the motor with the correct amount of voltage to counteract gravity and other forces.

Or is there?

Control systems are the solutions to the three problems mentioned above. They help programmers manipulate motors to a great deal of control and flexibility. When designing a control algorithm for a robot mechanism, there are a number of different approaches to take. These range from very simple approaches, to advanced and complex ones. Each has tradeoffs. Some will work better than others in different situations, some require more mathematical analysis than others.

There are two fundamental types of mechanism controllers that we will cover here:

**Feedforward control (or “open-loop control”)** refers to the class of algorithms which incorporate knowledge of how the mechanism under control is expected to operate. For example, using physics we can account for the force of gravity, friction, and other forces. Using this “model” of operation, the control input is chosen to make the mechanism get close to where it should be. In general, feedforward control is required whenever the system requires some constant control signal to remain at the desired setpoint (such as position control of a vertical arm where gravity will cause the arm to fall)

!!! note
    The Feedforward control section is still being worked on, so only Feedback control is discussed.

**Feedback control (or “closed-loop control”)** refers to the class of algorithms which use sensors to measure what a mechanism is doing, and issue corrective commands to move a mechanism from where it actually is, to where you want it to be. Even with unlimited study, it is impossible to know every force that will be exerted on a robot’s mechanism in perfect detail. So feedback control is used to correct the error not covered by feedforward control.

It is usually common and best to use both. In the next few pages, we will discuss both control types extensively.

## Trapezoid Profiles

It is often easy to get a motor to move at a certain velocity and acceleration using Feedforward + Feedback (which will be explained in the next few articles). However, what if we want a motor to turn to an exact position? In order to use feedforward effectively for position control, we need to come up with a sequence of velocities that will take the robot mechanism to the desire position. This is called a motion profile. 

In some situations, using a motion profile is overkill and feedback control is enough. We will explain why that sometimes pure pid or motion profiling would be for certain situations.