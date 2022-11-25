Now that we know the electronics on the robot, how do we actually control them? This section will provide a general overview, so if you want more details look in their respective javadocs and documentation.

## Motor Controllers/Motors
Typical motor controllers that you will be using are CANSparkMaxes and Talons. Their respective class names are "CANSparkMax" and "WPI_TalonSRX". There are other types of motor controllers. If you wish to know more about other motors, you can always look at their documentation. The information provided here should be present in all motor controllers.

### Creation

When creating Motor Controller objects, we do not actually use the conventional constructor to create them. We use a lib199 class called "MotorControllerFactory" to create the motors. However, it is worth highlighting how motor controllers are created conventionally so you can see why lib199's version is superior. We will demonstrate how to create a CANSparkMax using a constructor.

Referring to the javadocs for a [CANSparkMax](https://codedocs.revrobotics.com/java/com/revrobotics/cansparkmax), we can see that the constructor accepts two parameters: a deviceId (another name for port), and a Motor Type. The correct way to create a CANSparkMax object is as follows:

`CANSparkMax motor = new CANSparkMax([insert port number], MotorType.kBrushless);`

If you were to swap "kBrushless" with "kBrushed", you can end up destroying the motor since you are using the wrong motor type. Most motors using CANSparkMaxes are brushless.

Now let us use the lib199's MotorControllerFactory method.

`CANSparkMax motor = MotorControllerFactory.createSparkMax([insert port number], TemperatureLimit.NEO);`

You do not have to remember the specific types of motors used and will not risk destroying the motor. If you were to look at the [code for lib199](https://github.com/DeepBlueRobotics/lib199/blob/master/src/main/java/org/carlmontrobotics/lib199/MotorControllerFactory.java) and look at the createSparkMax method, there are a lot of features implemented to check for motor types, debugging, simulation, etc.
The method also asks for a temperature limit. In lib199, we have defined our temperature limits as enums. You just need to know the type of motor used, then you can put in "TemperatureLimit.[insert motor type]" as the parameter. The two types are NEO and NEO_550.

### Methods

Generally most motors should have the following methods. You can always go to their javadocs to find these methods.

- set(double speed)

    - Speed is an double value between -1 and 1
    - 1 means full voltage (full speed), 0 means no voltage (stopped), and similarly for any values in between

- Other setter methods

    - Can set voltage limits
    - Can also set encoders if the motor does not have build in encoders
    - setIdleMode(mode)
        
        - Brake: Will stop the motor quickly
        - Coast: Will let the motor spin down at its own rate
        - Ex: You probably want the motors to be in coast mode once the robot is disabled so it is easier to get balls out of the robot

- getter methods

    - Can get a variety of information on voltage, current, etc
    - Can also get the motor's encoder using getEncoder()

!!! warning
    Not all motor controllers have build in encoders, so some motor controllers do not have a "getEncoder()", rather you set their encoder using another method once you have attached a seperate encoder onto the motor.

- follow(Motor motor): can have a motor follow another motor's movements

### Debugging/Updating
- Motors have LED lights signifying their current status. You can find them on their website.
    
    - Ex: [CANSparkMax](https://docs.revrobotics.com/sparkmax/status-led)

- Other than javadocs, these websites provide more information on installation of motor controller (Sen-Ac's job)
- Sometimes you need to update the firmware on these motor controllers, in which case refer to the websites which usually have special software used for this purpose

## Encoders/Sensors
Some encoders are built into the motors such as CANSparkMaxes. There are also other encoders that have to be attached to a motor to function. We can also ultilize other sensors such as the gyro, accelerometer, and limelight, but in this section we will only cover encoders. If you wish to know more about the other sensors, please refer to the advanced programming section. If you want more details on [WPILIB encoders](https://docs.wpilib.org/en/stable/docs/software/hardware-apis/sensors/encoders-software.html#encoders-software), feel free to read up.

### Creation
Revrobotics uses the class ["RelativeEncoder"](https://codedocs.revrobotics.com/java/com/revrobotics/relativeencoder) for its encoders that are plugged into motor controllers. The lib199 "createCANEncoder" method may be deprecated (Ryan correct me if I'm wrong). Since the RelativeEncoder class is an interface, you cannot create an instance of class type RelativeEncoder. You have to use the .getEncoder() method of a revrobotics motor controller (assuming they have an encoder built in) to initialize a RelativeEncoder object.
```
CANSparkMax motor = MotorControllerFactory.createSparkMax(0);
RelativeEncoder encoder = motor.getEncoder();
```

Other encoders that are plugged directly into the roboRIO use WPILIB's ["Encoder"](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/wpilibj/Encoder.html) class. We use their conventional constructors, which typically ask for a port number.
`Encoder encoder = new Encoder([insert port 1], [insert port 2]);`

### Methods
Other than the information that can be obtained using getters on the motor controller object, encoders provide more information.

- Getter methods

    - The number of rotations the motor has spun 
    
        - Can determine distance motor traveled
    
    - The velocity

- Setter methods

    - Set Conversion Factors

        - Multiplies some constant to the original value to get another value
        - Can use math to get distance a wheel has traveled

- Reset Method (Zeroes the default position)

## Controllers
Here we will summarize the WPILIB classes used for communication between the driver computer and the roboRIO. There are several different types: Joystick, XboxController, and other class types. WPILIB offers a really good overview on [joysticks](https://docs.wpilib.org/en/stable/docs/software/basic-programming/joystick.html), so read up on that. We will cover how to mount commands to triggers once we have covered command-based programming.

## Pneumatics

!!! Unfinished

## Creating a Project
Alright, we've read a ton on these classes, but its no use if you don't practice implementing them! To make a project, clone the [EmptyProject2022](https://github.com/DeepBlueRobotics/EmptyProject2022) and create your own [branch](https://code.visualstudio.com/docs/sourcecontrol/overview#:~:text=You%20can%20create%20and%20checkout,tags%20in%20the%20current%20repository). We will cover in more detail about branches later.

Watch the [first video](https://www.youtube.com/watch?v=ihO-mw_4Qpo&t=347s&ab_channel=FRC0toAutonomous), and implement the code along the way. The first video should give you an overview of robot.java. In practice, we will not be implementing all our code in robot.java but in comamnds, subsystems, etc, which will be covered later. Note that instead of SparkMaxes he used Sparks, which should have very similar methods to SparkMaxes (Sparks were discontinued and SparkMaxes replaced them).

!!! warning
    Do not create a new project like how he did in the video. Use the cloned repo and do the code on your own branch.

Now watch the [second video](https://www.youtube.com/watch?v=g-dgdWVO5u8&t=1s&ab_channel=FRC0toAutonomous), but once he starts writing the code, **STOP**. He gives you all the motor controllers and encoders used for this project, so you can try implementing all the stuff before you see how he does it. It will involve searching up documentation a ton, so I highly recommend reading the "Finding JavaDocs and Resources" module (Some stuff may not make sense since we haven't covered Subsystems or Commands, so ignore those parts). For the ports, you can just put in random numbers as long as they are different from each other. Unfortunately, we do not have an exact replica of his robot to test the code on, but we will cover how to debug later on.

!!! warning
    It is crucial that you try to implement the code before seeing how someone else does it. It may involve a lot of googling and searching up documentation, but that is how programming will be like. It takes a ton of practice to get proficient at writing code.