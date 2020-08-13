# Introduction
If you have driven a robot around before (it's okay if you haven't), you might have noticed that you have to move the joysticks forward a fair amount in order to get the robot moving. Why is that?

Well, the motors have to overcome three primary obstacles: friction between the wheels and the surface, back-EMF, and motor resistance. In FRC competitions, the surface is a carpet with a static friction of about 1.0, which is considerable and should be accounted for in moving the drivetrain.

And Back-EMF? Well, EMF (electromotive force) is the force which causes electrons to move in circuits and is caused by a difference in electrical potential (voltage). When electricity passes through a DC motor, it goes through a copper coil. It is known that a charged copper coil creates a magnetic field and it is this magnetic field which spins the motor. However, this magnetic field also creates an electromotive force moving against the current flow. As a result, the motor will spin at a speed less than what was desired.

![DC Motor](https://blog.seeedstudio.com/wp-content/uploads/2019/04/DC_motor_rotor_labels-1030x773.jpg)

How do we account for friction, back-EMF, and motor resistance? We use a technique known as Drivetrain Characterization. In this section, we will develop the Drivetrain Characterization formula along with various tests to determine the constants required.

Let's go!

# Developing the Drivetrain Characterization Formula

# Determining the Constants

# Implementing Drivetrain Characterization