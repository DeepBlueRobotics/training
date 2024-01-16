How do you get the robot to move a certain distance? You could try to experimentally find the amount of time and power you need to run to get it to a distance, but that is not going to work for a variable distance or if there is a change in the environmental conditions such as the amount of friction.

How do you get a flywheel to move at a certain velocity? There is no `.setVelocity()` method that allows you to easily control a flywheel.

How do you get an arm to move to a certain position? Again, there is no `.setPosition()` method that can accurately supply the motor with the correct amount of voltage to counteract gravity and other forces.

Or is there?

Control systems are the solutions to the three problems mentioned above. They help programmers manipulate motors to a great deal of control and flexibility. When designing a control algorithm for a robot mechanism, there are a number of different approaches to take. These range from very simple approaches, to advanced and complex ones. Each has tradeoffs. Some will work better than others in different situations, some require more mathematical analysis than others.

There are two fundamental types of mechanism controller that we will cover here:

**Feedforward control (or “open-loop control”)** refers to the class of algorithms which incorporate knowledge of how the mechanism under control is expected to operate. For example, using physics we can account for the force of gravity, friction, and other forces. Using this “model” of operation, the control input is chosen to make the mechanism get close to where it should be.

**Feedback control (or “closed-loop control”)** refers to the class of algorithms which use sensors to measure what a mechanism is doing, and issue corrective commands to move a mechanism from where it actually is, to where you want it to be.

It is usually common and best to use both. In the next few pages, we will discuss both control types extensively.