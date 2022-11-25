Up until this point we have been working with a Differential Drivetrain. Using the joysticks, we tell the code how fast we should move forward/backward and how fast we should rotate (or, in the case of Tank Drive, how fast the left and right sides of the drivetrain should move). Swerve Drivetrains add one more controllable parameter: the speed and direction we can strafe (move sideways). With a Swerve Drivetrain, we can move forward, strafe, and rotate, even all three at the same time! As a result, we have a greater range of movement and it looks awesome!

<iframe width="560" height="315" src="https://www.youtube.com/embed/ro43JkcE8Fo" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>

This section will only cover how to program a Swerve Drivetrain using WPIlib. However, I highly recommend that you check out a description of the math at these resources:

- [Programming Swerve Drive](https://dominik.win/blog/programming-swerve-drive/) by Dominik Winecki.

- [SWERVE DRIVE](https://www.chiefdelphi.com/uploads/default/original/3X/e/f/ef10db45f7d65f6d4da874cd26db294c7ad469bb.pdf) (PDF) by Ether.

## Using WPIlib
Firstly, we need to create our kinematics and odometry objects. WPIlib has some documentation on how to construct a [kinematics object](https://docs.wpilib.org/en/stable/docs/software/kinematics-and-odometry/swerve-drive-kinematics.html) and an [odometry object](https://docs.wpilib.org/en/stable/docs/software/kinematics-and-odometry/swerve-drive-odometry.html), however I will also provide a simplified overview.

To do this, we need to specify the positions of each of the swerve modules. Then, we create our [`SwerveDriveKinematics`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/kinematics/SwerveDriveKinematics.html) and [`SwerveDriveOdometry`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/kinematics/SwerveDriveOdometry.html) objects, passing our kinematics object to the constructor of our odometry object.

``` Java
double wheelBase = /* The distance between the centers of wheels on the same side */;
double trackWidth = /* The distance between the centers of wheels on opposite sides */;

Translation2d locationFL = new Translation2d(wheelBase / 2, trackWidth / 2);
Translation2d locationFR = new Translation2d(wheelBase / 2, -trackWidth / 2);
Translation2d locationBL = new Translation2d(-wheelBase / 2, trackWidth / 2);
Translation2d locationBR = new Translation2d(-wheelBase / 2, -trackWidth / 2);

kinematics = new SwerveDriveKinematics(locationFL, locationFR, locationBL, locationBR);
odometry = new SwerveDriveOdometry(kinematics, new Rotation2d(getHeading()));
```

where FL corresponds to the the furthestmost module on the left side of the robot if you were at the back of the robot facing forward. As well, recall that an increase in x corresponds with moving forward, an decrease in x corresponds with moving backward, an increase in y corresponds with moving left, an decrease in y corresponds with moving right, an increase in rotation corresponds with turning counterclockwise, and a decrease in rotation corresponds with turning clockwise.

<a title="Truthdowser at English Wikipedia / CC BY (https://creativecommons.org/licenses/by/3.0)" href="https://commons.wikimedia.org/wiki/File:Wheelbase_and_Track.png"><img width="512" alt="Wheelbase and Track" src="https://upload.wikimedia.org/wikipedia/commons/5/52/Wheelbase_and_Track.png"></a>

<a href="https://commons.wikimedia.org/wiki/File:Wheelbase_and_Track.png" title="via Wikimedia Commons">Truthdowser at English Wikipedia</a> / <a href="https://creativecommons.org/licenses/by/3.0">CC BY</a>

Next, we want to calculate the [`SwerveModuleState`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/kinematics/SwerveModuleState.html) for each corner of the drivetrain. The way we do this depends on if our speeds are robot relative or field relative. If they are field relative, then we need to use [`ChassisSpeeds.fromFieldRelativeSpeeds`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/kinematics/ChassisSpeeds.html#fromFieldRelativeSpeeds(double,double,double,edu.wpi.first.wpilibj.geometry.Rotation2d)) and pass in our current heading.

``` Java
ChassisSpeeds speeds;
if (SmartDashboard.getBoolean("Field Relative", true)) {
    speeds = ChassisSpeeds.fromFieldRelativeSpeeds(forward, strafe, rotation, 
                                                   Rotation2d.fromDegrees(getHeading()));
} else {
    speeds = new ChassisSpeeds(forward, strafe, rotation);
}
SwerveModuleStates states[] = kinematics.toSwerveModuleStates(speeds);
```

Where forward, strafe, and rotation are our desired forward/backward speed in m/s, left/right speed in m/s, and counterclockwise angular speed in rad/s respectively.

What is a `SwerveModuleState`? It is an object that stores the speed, in m/s, at which to drive at and the angle, as a `Rotation2d` object, at which to orient the swerve module. So, `SwerveModuleState state = new SwerveModuleState(3.0, Rotation2d.fromDegrees(45));` represents a module running at 3.0 m/s facing 45 degrees. Simply put, a `SwerveModuleState` object represents a velocity vector for a particular swerve module.

Finally, we use our `SwerveModuleState` array to drive our motors. It's recommended that you create a separate class, perhaps named `SwerveModule`, for this purpose. Below, moduleFL and moduleFR are instances of such a class:

``` Java
/* Ensure that the speeds in the array of states are less than the maxSpeed of the robot, 
   but also ensure the ratio between speeds is the same. */
SwerveDriveKinematics.normalizeWheelSpeeds(states, maxSpeed);
moduleFL.move(states[0].speedMetersPerSecond / maxSpeed, states[0].angle.getRadians() / (2 * Math.PI));
moduleFR.move(states[1].speedMetersPerSecond / maxSpeed, states[1].angle.getRadians() / (2 * Math.PI));
/* Do the same for the rest of the modules. It is recommended that you create a 
SwerveModule class which handles moving the motors for a particular swerve module. */"
```

Now we need to create the `move()` method for our `SwerveModule` class. It should take the desired speed as a fraction of our maximum speed and the desired angle as a fraction of \(2\pi\). Our method should compute how much the turn motor (the motor responsible for turning module) should move and set the speed of the drive motor (the motor responsible for rotating the wheel). We can do this with three methods: `computeSetpoints()`, `shouldReverse()`, and `convertAngle()`.

``` Java
/**
     * Computes the setpoint values for speed and angle for a singular motor controller.
     * 
     * @param normalizedSpeed   The desired normalized speed, from -1.0 to 1.0.
     * @param angle             The desired angle, from -1.0 to 1.0.
     * @param encoderPosition   The position of the <i> quadrature </i> encoder on the turn motor controller.
     * @param gearRatio         The gear ratio of the turn motor controller.
     * @return An array of doubles containing the setpoint values in the order of speed then angle.
     */
    public static double[] computeSetpoints(double normalizedSpeed, double angle, double encoderPosition, double gearRatio) {
        double newAngle = convertAngle(angle, encoderPosition, gearRatio);
        double speed = normalizedSpeed;
		
		if (shouldReverse(newAngle, encoderPosition, gearRatio)) {
			if (newAngle < 0) newAngle += 0.5;
			else newAngle -= 0.5;
			speed *= -1.0;
        }
		
		return new double[]{speed, newAngle};
    }

    /**
     * Determines whether or not the robot should take the reverse direction to get to the desired angle. 
     * e.g. if the robot was to turn 3pi/2 radians clockwise, it would be better to turn pi/2 radians counter-clockwsie.
     * Credit to Team 100 for their code.
     * 
     * @param angle             The desired angle between -0.5 and 0.5
     * @param encoderPosition   The position of the <i> quadrature </i> encoder on the turn motor controller.
     * @param gearRatio        The gear ratio of the turn motor controller.
     * @return A boolean representing whether the robot should reverse or not.
     */
    public static boolean shouldReverse(double angle, double encoderPosition, double gearRatio){
        double convertedEncoder = (encoderPosition / gearRatio) % 1;
        // Convert the angle from -0.5 to 0.5 to 0 to 1.0
        if (angle < 0) angle += 1;
        
        double longDifference = Math.abs(angle - convertedEncoder);
        double difference = Math.min(longDifference, 1.0 - longDifference);
        // If the difference is greater than 1/4, then return true (it is easier for it to turn around and go backwards than go forward)
        if (difference > 0.25) return true;
        else return false;
    }
    /**
     * Converts the angle from radians to a percentage of encoder ticks. Credit to Team 100 for their code.
     * 
     * @param angle             The desired angle from -1.0 to 1.0
     * @param encoderPosition   The position of the <i> quadrature </i> encoder on the turn motor controller.
     * @param gearRatio        The gear ratio of the turn motor controller.
     * @return The converted angle between -0.5 and 0.5.
     */
    public static double convertAngle(double angle, double encoderPosition, double gearRatio) {
        double encPos = encoderPosition / gearRatio;

        double temp = angle;
        temp += (int)encPos;

        encPos = encPos % 1;

        if ((angle - encPos) > 0.5) temp -= 1;
        if ((angle - encPos) < -0.5) temp += 1;

        return temp;
    }
```

`computeSetpoints()` uses the other two functions in order to compute the desired speed as a percentage of maximum applied voltage (from -1.0 to 1.0) and the angle as a percentage of a full rotation of the encoder on the turn motor. `shouldReverse()` determines whether or not the module should turn to the desired angle (\(\alpha\)) or if it should turn to \(\alpha + 180^{\circ}\) and run the drive motor in the opposite direction (which has the same result as turning to \(\alpha\) but may be faster). `convertAngle()` converts the angle from a fraction of a full revolution in radians to a fraction of a full revolution in quadrature encoder ticks.

We can combine these three methods in our `move()` function as follows:

``` Java
public void move(double normalizedSpeed, double angle) {
    double setpoints[] = SwerveMath.computeSetpoints(normalizedSpeed / maxSpeed,
                                                     angle / (2 * Math.PI),
                                                     turn.getSelectedSensorPosition(0),
                                                     gearRatio);
    setSpeed(setpoints[0]);
    if(setpoints[0] != 0.0) setAngle(setpoints[1]);
}
```

## Home Absolute
Currently, our `move()` method requires that our swerve modules measure their orientation as a counter-clockwise angle relative to facing straight forward so that passing an angle of 0 makes it face forward, 0.5 makes it face backward, etc. This is where the `HomeAbsolute` command comes in. `HomeAbsolute` requires knowing two things: the quadrature/analog position of the initial configuration and the gear ratio of the turn motor. We calculate our displacement from the configuration we want and set the sensor position to this displacement. As a result, whenever we read from this sensor (or direct a motor controller to go to a specific position), the sensor value will be relative to the intial configuration. Here is the bulk of the command for Talon_SRX motor controllers:

``` Java
// The quadrature encoders are for turning the steer motor.
// The analog encoders are for checking if the motors are in the right position.
turn.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

// Change the current quadrature encoder position to the difference between the zeroed position and the current position, as measured by the analog encoder.
// Difference is in analog encoder degrees which must be converted to quadrature encoder ticks.
// Max value of the analog encoder is MAX_ANALOG, min value is 0.
int quadPos = (int) ((Math.abs(gearRatio) / maxAnalog) * (turn.getSensorCollection().getAnalogInRaw() - turnZero));

// Set the orientation of the modules to what they would be relative to TURN_ZERO.
turn.setSelectedSensorPosition(quadPos);

// Make sure we actually turn to the correct position.
setAngle(0.0);      // Your method for setting the angle of the module.
```

## Swerve Drivetrain Odometry

Updating the robot's odometry for swerve drivetrains is similar to updating it for differential drivetrains. You can declare a [`SwerveDriveOdometry`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/kinematics/SwerveDriveOdometry.html) object with:

``` Java
SwerveDriveOdometry odometry = new SwerveDriveOdometry(/* initialPose */, /* robot heading */);
```

And to update your `SwerveDriveOdometry` object:

``` Java
odometry.update(/* robot heading */, /* SwerveModuleState objects */);
```

The `SwerveModuleState` objects correspond to the actual speed and angle of the swerve modules, not the `SwerveModuleState` objects calculated using your `SwerveDriveKinematics` object. The order of the arguments should correspond with the order you specified in the constructor for your `SwerveDriveKinematics` object. For example, if you wrote: 

``` Java
// Intentionally shuffled the locations to show that order matters
SwerveDriveKinematics kinematics = new SwerveDriveKinematics(locationBR, locationFL, locationBL, locationFR);
```

Then you would update your odometry with:

``` Java
// stateFL, stateFR, etc. are SwerveModuleState objects
odometry.update(/* robot heading */, stateBR, stateFL, stateBL, stateFR);
```
