## Command-based programming

FRC has a pretty good write-up on command-based programming, so we'll just read that. Once you're done, we'll get to some fun stuff.

<https://frc-docs.readthedocs.io/en/latest/docs/software/old-commandbased/index.html>

!!! note
    FRC is currently making major revisions to the command-based system that will be released for the 2020 build season. The general concepts will be the same, but don't take all the class names and details to heart since they are most likely going to change.

## Let's get our hands dirty

### Adding motor controllers

First order of business is to add the two other motor controllers (Victor SPX and Victor SP) on the practice board to the code. That involves three steps:

1. Creating instances of them in `RobotMap.java`
2. Adding them as constructor parameters to the `Motors.java` subsystem and calling their `set()` methods in `Motors.drive()`
3. Accessing them from the RobotMap and passing them into the Motors subsystem when they're created in `Robot.java`

Use the `talon` object as an example. The Victor SPX class is called `WPI_VictorSPX` and is located in the `com.ctre.phoenix.motorcontrol.can` package, while the Victor SP class is called `VictorSP` and is located in the `edu.wpi.first.wpilibj` package. 

Now, when you deploy the robot code to the practice board and execute the `RunMotor` command, do all of the motor controllers run?

### Running the motors with joystick input

It's a little boring right now, since you can only run the motors at a certain speed. Let's create a command that runs them based on how far we tilt the joystick. 

To create a command, right click on the `commands` directory in VSCode and select "Create a new class/command". After that, select "Command" and name it `RunMotorsWithJoystick`. 

Pass the `Motors` subsystem and a `Joystick` object into the command and have it drive the motors to the Y-value of the joystick when executed. Use the prewritten code, the command-based programming tutorial on the top of this page, and the WPILib JavaDocs (which can be found in the "Resources" section of this site) for help. 

!!! tip
    Remember, if you need help, don't be afraid to ask! We're throwing you in the deep end right now because it's the fastest way to learn. You're not expected to immediately know what to do.

### Tying it all up

Once you are done writing the command, make it the default command of the `Motors` subsystem. Do that by calling `motors.setDefaultCommand()` in `OI.java` and pass in the left joystick.

Deploy it again and try it out!