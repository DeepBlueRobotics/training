This section is a broad overview of the Kinematics and Odometry classes in WPIlib. For a more detailed description, you can check out their section of the wiki [here](https://docs.wpilib.org/en/stable/docs/software/kinematics-and-odometry/index.html).

## What is Odometry?
Odometry is the use of sensor information to estimate the robot's position and rotation on the field. Odometry works for both differential drivetrains and swerve drivetrains (covered in a later section - drivetrains that can move forward, strafe, and rotate, perhaps all three at the same time).

## WPILib Odometry Class
Before we cover odometry, we first need to cover the [`Translation2d`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/geometry/Translation2d.html), [`Rotation2d`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/geometry/Rotation2d.html), and [`Pose2d`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/geometry/Pose2d.html) objects.

[`Translation2d`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/geometry/Translation2d.html) stores x and y positions of the robot in meters as well as has several other useful methods, including [`plus()`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/geometry/Translation2d.html#plus(edu.wpi.first.wpilibj.geometry.Translation2d)), [`rotateBy()`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/geometry/Translation2d.html#rotateBy(edu.wpi.first.wpilibj.geometry.Rotation2d)), [`times()`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/geometry/Translation2d.html#times(double)), and [`getNorm()`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/geometry/Translation2d.html#getNorm()) (normalization a.k.a the unit vector that points in the same direction as the point).

[`Rotation2d`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/geometry/Rotation2d.html) stores an angular position value in radians as well as has several other useful methods, including [`getDegrees()`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/geometry/Rotation2d.html#getDegrees()), [`fromDegrees()`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/geometry/Rotation2d.html#fromDegrees(double)), [`rotateBy()`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/geometry/Rotation2d.html#rotateBy(edu.wpi.first.wpilibj.geometry.Rotation2d)), and [`plus()`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/geometry/Rotation2d.html#plus(edu.wpi.first.wpilibj.geometry.Rotation2d)).

[`Pose2d`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/geometry/Pose2d.html) is a combination of [`Translation2d`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/geometry/Translation2d.html) and [`Rotation2d`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/geometry/Rotation2d.html#plus(edu.wpi.first.wpilibj.geometry.Rotation2d)) into a singular class, alongside methods such as [`plus()`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/geometry/Pose2d.html#plus(edu.wpi.first.wpilibj.geometry.Transform2d)) and [`relativeTo()`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/geometry/Pose2d.html#relativeTo(edu.wpi.first.wpilibj.geometry.Pose2d)).

To declare an odometry object, you need to specify the robot's pose and heading. For [`DifferentialDriveOdometry`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/kinematics/DifferentialDriveOdometry.html), you can do this as follows:

``` Java
Rotation2d heading = /* Get gyro heading */;
// Create odometry at (0, 0) at a specific heading
DifferentialDriveOdometry odometry1 = new DifferentialDriveOdometry(heading)
// Or...
// Create odometry at (x, y) at a specific heading
DifferentialDriveOdometry odometry2 = new DifferentialDriveOdometry(heading, new Pose2d(x, y, heading))
```

Note that when initializing the odometry with only a heading, the angle in the `Pose2d` object will _not_ be equal to the heading. The `Pose2d` angle is relative to its initial heading.

``` Java
// Create an odometry at (0, 0) facing 90 degrees.
DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(90));
// Print the current rotation. Should be 0 degrees.
System.out.println(odometry.getPoseMeters().getRotation().toDegrees());
// Update the odometry with a new heading
odometry.update(Rotation2d.fromDegrees(225), 0, 0);
// Should be 135 degrees after update
System.out.println(odometry.getPoseMeters().getRotation().toDegrees());
```

The odometry object has three methods:

- [`getPoseMeters()`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/kinematics/DifferentialDriveOdometry.html#getPoseMeters()) = returns a Pose2d object representing the robot's current position on the field and rotation.

- [`resetPosition()`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/kinematics/DifferentialDriveOdometry.html#resetPosition(edu.wpi.first.wpilibj.geometry.Pose2d,edu.wpi.first.wpilibj.geometry.Rotation2d)) = resets the odometry with a new pose and heading.

- [`update()`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/kinematics/DifferentialDriveOdometry.html#update(edu.wpi.first.wpilibj.geometry.Rotation2d,double,double)) = updates the odometry pose based on the robot's heading and speed.

## What is Kinematics?
Drivetrain kinematics deals with the relationship between motor speeds and the velocity of the drivetrain. There are two types of kinematic calculations: forward kinematics and inverse kinematics. Forward kinematics calculates the velocity of the drivetrain based on the motor speeds and inverse kinematics calculates the motor speeds based on the velocity of the drivetrain. Like with odometry, there are implementations of kinematics for differential drivetrains and swerve drivetrains.

## WPILib Kinematics Class
Before we cover kinematics, we first need to cover the [`ChassisSpeeds`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/kinematics/ChassisSpeeds.html) class. `ChassisSpeeds` is a simple class that contains information about the translational velocity (forward/backward and left/right) and angular velocity (rotation) of the robot. `ChassisSpeeds` has three primary variables:

- vxMetersPerSecond = the *forward* velocity of the drivetrain in m/s. + means moving forward, - means moving backward.

- vyMetersPerSecond = the *lateral* velocity of the drivetrain in m/s. + means moving left, - means moving right.

- omegaRadiansPerSecond = the angular velocity of the drivetrain in rad/s. + means moving counter-clockwise, - means moving clockwise.

Try not to confuse your coordinate systems! X means going forward/backward and Y means going to the left/right.

The [Kinematics object](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/kinematics/DifferentialDriveKinematics.html) can be constructed as follows for a Differential Drivetrain:

``` Java
// trackWidthMeters is a double representing the distance, in meters, between two opposite wheels on the drivetrain.
DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics​(trackWidthMeters);
```

The kinematics class has two useful functions:

- [`toChassisSpeeds()`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/kinematics/DifferentialDriveKinematics.html#toChassisSpeeds(edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds)) = takes a [`DifferentialDriveWheelSpeeds`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/kinematics/DifferentialDriveWheelSpeeds.html) object (representing the speeds, in m/s, of the left and right side of the drivetrain) and returns a [`ChassisSpeeds`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/kinematics/ChassisSpeeds.html) object.

- [`toWheelSpeeds()`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/kinematics/DifferentialDriveKinematics.html#toWheelSpeeds(edu.wpi.first.wpilibj.kinematics.ChassisSpeeds)) = takes a [`ChassisSpeeds`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/kinematics/ChassisSpeeds.html) object and returns a [`DifferentialDriveWheelSpeeds`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/kinematics/DifferentialDriveWheelSpeeds.html) object.