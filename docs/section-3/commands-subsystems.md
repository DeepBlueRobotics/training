## Command-based programming

Youtube has a really good [series](https://www.youtube.com/channel/UCmJAoN-yI6AJDv7JJ3372yg/videos) on programming using WPILib. I highly recommend watching videos #1, 2, and 5. All the others are optional and are more advanced, but feel free to look at them. Try implementing the code along with the video so you get a feel of things.

FRC also has [a pretty good write-up on command-based programming](https://docs.wpilib.org/en/latest/docs/software/commandbased/index.html). The video covers all the fundamentals so it isn't required that you read this. However, the write-ups go into more detail. These resources are crucial and will help you understand nearly everything about writing code for the robot. 

Do not skip past this unless you understand how it works!

## Let's write some robot code!

### Adding motor controllers

First order of business is to add the two other motor controllers (Victor SPX and Spark Max) on the practice board to the code. That involves two steps:

1. Creating instances of them in `Motors.java`
2. Calling their `set()` methods in `Motors.run()`

Use the `talon` object as an example for the Victor SPX and Spark MAX objects. The Victor SPX class is called `WPI_VictorSPX` and is located in the `com.ctre.phoenix.motorcontrol.can` package, while the Spark Max class is called `CANSparkMax` and is located in the `com.revrobotics` package.

Once you're done with that, do not deploy from your computer. Follow the instructions in the next page, `Advanced Version Control`, and then pull your branch onto the driver station computer that should be set up and deploy from there.

Now, when you deploy the robot code to the practice board and execute the `RunMotor` command, do all of the motor controllers run?

### Running the motors with joystick input

It's a little boring right now, since you can only run the motors at a certain speed. Let's create a command that runs them based on how far we tilt the joystick.

To create a command, right click on the `commands` directory in VSCode and select "Create a new class/command". After that, select "Command" and name it `RunMotorsWithJoystick`.

Pass the `Motors` subsystem and a `Joystick` object into the command and have it drive the motors to the Y-value of the joystick when executed. Use the prewritten code, the command-based programming tutorial on the top of this page, and the WPILib JavaDocs for help. 

!!! tip
    Remember, if you need help, don't be afraid to ask! We're throwing you in the deep end right now because it's the fastest way to learn. You're not expected to immediately know what to do.

### Tying it all up

Once you are done writing the command, make it the default command of the `Motors` subsystem. Do that by calling `motors.setDefaultCommand()` in `RobotContainer.java` and pass in the left joystick.

Again, commit and push your changes, then pull on the driver station computer and deploy!
