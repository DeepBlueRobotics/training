## Before you start coding autonomous

Before you can code your autonomous period, you'll need to find the answer to a few more questions:

- What ports are we plugging the encoders into?
- What is the distance per pulse of the drivetrain encoders?

## Autonomous

Once you've successfully written code for the teleop period, it's time to move to autonomous. 

Your autonomous period should consist of two things First, move the robot 2 feet forward. Then, spin the robot around in circles for the remainder of the autonomous period.

More requirements for autonomous:

- it must use a command group (look in the documentation if you forgot what that is) to run the autonomous period, with one command for moving and the other for spinning
- it can only use the encoder values to tell the command whether or not you have traveled far enough
- it should not overshoot by a wide margin, but as long as it goes past 2 feet it's acceptable

Good luck! Again, once you're done, simulate it and then check in with a programming lead or mentor then run your code on a robot!