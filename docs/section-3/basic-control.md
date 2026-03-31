Now that we know the electronics on the robot, how do we actually control them? This section will provide a general overview, so if you want more details look in their respective javadocs and documentation.

## Motor Controllers/Motors
Typical motor controllers that you will be using are Spark Maxes and Spark Flexes. Their respective class names are `SparkMax` and SparkFlex`. There are other types of motor controllers. If you wish to know more about other motors, you can always look at their documentation. The information provided here should be present in all motor controllers.

### Creation

When creating Motor Controller objects, we do not actually use the conventional constructor to create them. We use a lib199 class called `MotorControllerFactory` to create the motors. However, it is worth highlighting how motor controllers are created conventionally so you can see why lib199's version is superior. We will demonstrate how to create a SparkMax using a constructor.

Referring to the javadocs for a [SparkMax](https://codedocs.revrobotics.com/java/com/revrobotics/spark/sparkmax), we can see that the constructor accepts two parameters: a device id (another name for port), and a motor type. The correct way to create a SparkMax object is as follows:

``` Java
SparkMax motor = new SparkMax([insert port number], MotorType.kBrushless);
```

The two motor types: `kBrushless` and `kBrushed` require the controller to power the motors in different ways, and you can end up destroying the motor by using the wrong motor type. The easiest way to tell the difference is that brushless motors use three wires while brushed motors use two. Most of the motors we will use with SparkMaxes are brushless.

Lib199 is our custom java library which contains many methods and objects we reuse over many projects and years. Now let's use lib199's MotorControllerFactory method to create a SparkMax.

``` Java
SparkMax motor = MotorControllerFactory.createSparkMax([motor id], MotorConfig.NEO);
```

You do not have to remember the specific types of motors used and will not risk destroying the motor. If you were to look at the [`createSparkMax` in lib199](https://github.com/DeepBlueRobotics/lib199/blob/master/src/main/java/org/carlmontrobotics/lib199/MotorControllerFactory.java), there are a lot of features implemented to check for motor types, debugging, simulation, etc.
The method also asks for a motor configuration. In lib199. All You need to know is the type of motor used, then you can put in "MotorConfig.[insert motor type]" as the parameter. The types are `NEO`, `NEO_VORTEX`,  `NEO-550`, `NEO_550`, `NEO_2`, and `NEO_SOLO_VORTEX`.

### Methods

Generally most motors will have the following methods. You can always go to their javadocs to find these methods.

- set(double speed)

    - Speed is an double value between -1 and 1
    - 1 means full voltage (full speed), 0 means no voltage (stopped), and similarly for any values in between

- Other methods

    - Can set voltage limits
    - Can also set encoders if the motor does not have build in encoders
    - setIdleMode(mode)
        - Determines what the motor will do if there is no input
        - Brake: Will stop the motor quickly
        - Coast: Will let the motor spin down at its own rate
    - follow(Motor motor): can have a motor follow another motor's movements
    - getter methods
        - Can get a variety of information on voltage, current, RPM etc
        - Can also get the motor's encoder using getEncoder()

!!! warning
    Not all motor controllers have built-in encoders, so some motor controllers do not have a `getEncoder()`, rather you set their encoder using another method once you have attached a seperate encoder onto the motor.

### Debugging/Updating
- Motors have LED lights signifying their current status. You can find them on their website.
    
    - [SparkMax](https://docs.revrobotics.com/brushless/spark-max/status-led)
    - [SparkFlex](https://docs.revrobotics.com/brushless/spark-flex/status-led)

- Other than javadocs, these websites provide more information on installation of motor controller (Senac's job)
- Sometimes you need to update the firmware on these motor controllers, in which case refer to the websites which usually have special software used for this purpose.
- Motor controllers will sometimes need software updates, which can be done by connecting to the motor with a USB-C cable and using Rev Hardware Client 2 (NOT 1).

## Encoders/Sensors
Some encoders are built into the motors such as NEOs and NEO 550s. There are also other encoders that have to be attached to a motor to function. We can also ultilize other sensors such as the gyro, accelerometer, and limelight, but in this section we will only cover encoders. If you wish to know more about the other sensors, please refer to the advanced programming section or ask a programming veteran. If you want more details on [WPILib encoders](https://docs.wpilib.org/en/stable/docs/software/hardware-apis/sensors/encoders-software.html#encoders-software), feel free to read up.

### Creation
Revrobotics uses the class [`RelativeEncoder`](https://codedocs.revrobotics.com/java/com/revrobotics/relativeencoder) for its encoders that are plugged into motor controllers. Since the `RelativeEncoder` class is an interface, you cannot create an instance of class type RelativeEncoder. You have to use the `.getEncoder()` method of a revrobotics motor controller (assuming they have an encoder built in) to initialize a `RelativeEncoder` object.
``` Java
SparkMax motor = MotorControllerFactory.createSparkMax(1);
motorConfig = MotorControllerFactory.sparkConfig(MotorConfig.[motor type]);
RelativeEncoder encoder = motor.getEncoder();
```

Other encoders that are plugged directly into the roboRIO use WPILib's [Encoder](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/wpilibj/Encoder.html) class. We use their conventional constructors, which typically ask for a port number.
``` Java
Encoder encoder = new Encoder([insert port 1], [insert port 2]);
```

### Methods
Other than the information that can be obtained using getters on the motor controller object, encoders provide more information.

- Getter methods

    - The number of rotations the motor has spun 
    - The RPM

- Setter methods

    - Set Conversion Factors

        - Multiplies some constant by the original value to get another value
        - Can use math to get distance a wheel has traveled

- Reset Method (Zeroes the default position)

## Controllers
Here we will summarize the WPILib classes used for communication between the driver computer and the roboRIO. There are several different types: Joystick, XboxController, and other class types. All of these classes extend the `GenericHID` class, which is the general class that implements controllers. WPILib offers a really good overview on [joysticks](https://docs.wpilib.org/en/stable/docs/software/basic-programming/joystick.html), so read up on that. We will cover how to mount commands to triggers once we have covered command-based programming. Here is a general overview.

### Creation
WPILib provides several classes such as `Joystick` and `XBoxController`. We use their conventional constructors which both ask for a port number:
``` Java
Joystick joystick = new Joystick([insert port]);
XBoxController xbox = new XBoxController([insert port]);
```
You can configure their USB port numbers through driverstation. A more detailed guide is outlined in the WPILib article about joysticks.

### Methods
[Controllers](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/wpilibj/GenericHID.html) provide data on the axis, buttons, and other inputs. They usually have a lot of getter methods and a few config methods.

## Pneumatics
Pneumatics use compressed air to actuate mechanisms. They are often used when we want part of our robot to actuate between two set positions. Once again, WPILib has a [good article](https://docs.wpilib.org/en/stable/docs/software/hardware-apis/pneumatics/pneumatics.html) which explains this more in-depth.

### Creation
For pneumatics, WPILib provides two clases: [`Solenoid`](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/wpilibj/Solenoid.html) and [`DoubleSolenoid`](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/wpilibj/DoubleSolenoid.html). Solenoids are turned on to extend and when turned off retract with the help of a spring. In contrast, double solenoids use compressed air to both extend and retract the solenoid. The contructors look like this:
``` Java
Solenoid solenoid = new Solenoid(PneumaticsModuleType.CTREPCM, [insert port]);
DoubleSolenoid doubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, [insert port 1], [insert port 2]);
```

There are currently two different modules for controlling pneumatics: the CTRE Pnumatic Control Module and the Rev Robotics Pneumatic Hub. For the time being, we use the one created by CTRE.

### Methods
Both solenoid objects provide two main functionalities: getting the state of solenoid and setting the state of the solenoid. These can be accomplished with the use of the `get`, `set`, and `toggle` methods. It's important to note that while `Solenoid` represents its state with a `boolean`, `DoubleSolenoid` uses the enum `DoubleSolenoid.Value`. This can have three possible states:

- `kOff` - No air is provided to the solenoid  
- `kForward` - Presure is provided to extend the solenoid  
- `kReverse` - Presure is provided to retract the solenoid  
!!! note
    For a `DoubleSolenoid`, the `toggle` method will have no effect if the state is `kOff`

## Creating a Project
Alright, we've read a ton on these classes, but its no use if you don't practice implementing them! To make a project, clone the newest EmptyProject repository and create your own branch. We will cover in more detail about branches later.

Watch the [first video](https://www.youtube.com/watch?v=ihO-mw_4Qpo&t=347s&ab_channel=FRC0toAutonomous), and implement the code along the way. The first video should give you an overview of robot.java. In practice, we will not be implementing all our code in robot.java but in comamnds, subsystems, etc, which will be covered later. Note that instead of Spark Maxes he used Sparks, which should have very similar methods to Spark Maxes (Sparks were discontinued and Spark Maxes replaced them).

!!! warning
    Do not create a new project like how he did in the video. Use the cloned repo and do the code on your own branch.

Now watch the [second video](https://www.youtube.com/watch?v=g-dgdWVO5u8&t=1s&ab_channel=FRC0toAutonomous), but once he starts writing the code, **STOP**. He gives you all the motor controllers and encoders used for this project, so you can try implementing one of the subsystems before you see how he does it (If you have time to implement the other subsystems, go ahead). It will involve searching up documentation a ton, so I highly recommend reading the [Finding JavaDocs and Resources](finding-docs.md) module (Some stuff may not make sense since we haven't covered Subsystems or Commands, so ignore those parts). For the ports, you can just put in random numbers as long as they are different from each other. Unfortunately, we do not have an exact replica of his robot to test the code on, but we will cover how to debug later on.

!!! warning
    It is crucial that you try to implement the code before seeing how someone else does it. It may involve a lot of googling and searching up documentation, but that is how programming will be like. It takes a ton of practice to get proficient at writing code.

!!! tip
    Remember, if you need help, don't be afraid to ask! We're throwing you in the deep end right now because it's the fastest way to learn. You're not expected to immediately know what to do.