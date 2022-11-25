## Command-based programming

Youtube has a really good [series](https://www.youtube.com/channel/UCmJAoN-yI6AJDv7JJ3372yg/videos) on programming using WPILib. I highly recommend watching videos #1, 2, and 5. All the others are optional and are more advanced, but feel free to look at them. Try implementing the code along with the video so you get a feel of things.



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


Different section


## Deploying robot code

!!! warning "Before you start"
    This part needs:

    - a practice electronics board that has a Talon SRX
    - a computer with driver station software installed
    - a controller connected to the computer  
    - a brushed motor

Clone the [`TrainingRobot`](https://github.com/DeepBlueRobotics/TrainingRobot) repo. Now connect to the RoboRIO of the practice board with a USB cable, click on the WPILib logo on the top right of VSCode which looks like this: ![wpilib](https://avatars1.githubusercontent.com/u/19267233?s=400&v=4){: style="height:25px;width:25px"}, and then choose "Deploy Robot Code". It should take a minute to deploy the code before displaying "Build Successful" in green. 

!!! note
    Deploying code to the RoboRIO only works on a Windows computer; however, you can still build your code to see if it compiles correctly before moving onto a Windows computer to complete this part.

Once you have that, connect a driver station computer to the RoboRIO and a motor to the Talon, enable, and press the X key on the controller. If the motor runs, then you're all set!

## Using WPILib

You may have noticed that the motor does not stop running when you release the button. Let's change the code so that it runs as long as you press the button.

**Step 1: Change the button to trigger the command continuously while it's held rather than once when it's pressed**

In the `TrainingRobot` project, find the `RobotContainer.java` file (`src/main/java/org/team199/trainingrobot/RobotContainer.java`). Currently, line 20 contains the method `whenPressed()`. Refer to the WPILib JavaDocs to determine what you need to change `whenPressed()` to in order to run the command while the button is pressed. (Hint: `runMotorButton` is an instance of the `JoystickButton` class.)

**Step 2: Stop the motors when the command ends**

In the same project, you'll find the `RunMotor.java` file in the `commands` directory. In the `RunMotor` class, the `end()` method is currently empty. Add code to it that stops the motors. (Hint: you might want to write something similar to what's in `execute()`.)

**Step 3: Deploy!**

Once you think you figured it out, follow the above "Deploying robot code" section again to deploy, and see if your code worked!