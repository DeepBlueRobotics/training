# What is Odometry?
Odometry is the use of sensor information to estimate the robot's position on the field. Odometry works for both differential drivetrains (think arcade/tank drive) and swerve drivetrains (covered in a later section - drivetrains that can move forward, strafe, and rotate, perhaps at the same time).

# WPILib Odometry Class
Before we cover odometry, we first need to cover the `Translation2d`, `Rotation2d`, and `Pose2d` objects.

`Translation2d` stores x and y positions of the robot in meters as well as has several other useful methods, including scaling using `plus()`, `rotateBy()`, `times()`, and `getNorm()` (normalization a.k.a the unit vector on the unit circle in the same direction as the point).

`Rotation2d` stores an angular position value in radians as well as has several other useful methods, including `getDegrees()`, `fromDegrees()`, `rotateBy()`, and `plus()`.

`Pose2d` is a combination of `Translation2d` and `Rotation2d` into a singular class, alongside methods such as `plus()` and `relativeTo()`.

To declare an odometry object, you need to specify the robot's pose and heading. For DifferentialDriveOdometry, you can do this as follows:

```
Rotation2d heading = /* Get gyro heading */;
// Create odometry at (0, 0)
DifferentialDriveOdometry odometry1 = new DifferentialDriveOdometry(heading)
// Or...
// Create odometry at (x, y)
DifferentialDriveOdometry odometry2 = new DifferentialDriveOdometry(heading, new Pose2d(x, y, heading))
```

The odometry object has three methods of importance:

- `getPoseMeters()` = returns a Pose2d object representing the robot's current position on the field.

- `resetPosition()` = resets the odometry with a new pose and rotation.

- `update()` = updates the odometry pose based on the robot's new heading and speeds.

# What is Kinematics?
Kinematics deals with calculating the deisred velocities of the motor controllers based on the desired velocity of the drivetrain. As with odometry, there are implementations of kinematics for differential drivetrains and swerve drivetrains.

# WPILib Kinematics Class
Before we cover kinematics, we first need to cover the `ChassisSpeeds` class. `ChassisSpeeds` is a simple class that contains information about the translational velocity (forward/backwards and left/right) and angular velocity (rotation) of the robot. `ChassisSpeeds` has three primary variables:

- vxMetersPerSecond = the *forward* velocity of the drivetrain in m/s. + means moving forward, - means moving backwards.

- vyMetersPerSecond = the *horizontal* velocity of the drivetrain in m/s. + means moving left, - means moving right.

- omegaRadiansPerSecond = the angular velocity of the drivetrain in rad/s. + means moving counter-clockwise, - means moving clockwise.

Try not to confuse your coordinate systems! X means going forwards and Y means going horizontal.

The Kinematics object can be constructed as follows for a Differential Drivetrain:

```
// trackWidthMeters is a double representing the distance, in meters, between two opposite wheels on the drivetrain.
DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics​(trackWidthMeters);
```

The kinematics class has two useful functions:

- `toChassisSpeeds()` = takes a `DifferentialDriveWheelSpeeds` object (representing the speeds, in m/s, of the left and right side of the drivetrain) and returns a `ChassisSpeeds` object.

- `toWheelSpeeds()` = takes a `ChassisSpeeds` object and returns a `DifferentialDriveWheelSpeeds` object.