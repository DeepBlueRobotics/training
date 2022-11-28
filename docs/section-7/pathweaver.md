One advantage of a characterized drivetrain is that it more effectively drives the robot to a precise location. This makes following autonomous paths a more viable option. All we need to do is tell the robot a specific path to follow and the robot will drive the path pretty accurately.

But how do we specify these paths? We use the `Trajectory` class along with WPILib's Pathweaver tool. For a more detailed description of how to use this class, check out WPIlib's [documentation](https://docs.wpilib.org/en/stable/docs/software/examples-tutorials/trajectory-tutorial/index.html).

Let's write some autonomous code!

## Trajectories
The [`Trajectory`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/trajectory/Trajectory.html) class creates a smooth path through a list of states. Each state contains information such as the position on the field, time elapsed, velocity, acceleration, pose, and the curvature of the path. Once you have built your paths using Pathweaver, you can use the [`fromPathweaverJson()`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/trajectory/TrajectoryUtil.html#fromPathweaverJson(java.nio.file.Path)) method from [`TrajectoryUtil`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/trajectory/TrajectoryUtil.html) to create a Trajectory from the built path. This will be the primary way in which we create our `Trajectory` objects.

If you want to create a trajectory given a list of points, you can use the [`TrajectoryGenerator`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/trajectory/TrajectoryGenerator.html) class. There are many ways to generate a trajectory using this option, but I will only highlight the [`generateTrajectory()`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/trajectory/TrajectoryGenerator.html#generateTrajectory(java.util.List,edu.wpi.first.wpilibj.trajectory.TrajectoryConfig)) method which uses a list of waypoints and a [`TrajectoryConfig`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/trajectory/TrajectoryConfig.html) object. For the `TrajectoryConfig`, there are several constraints we can add. As an example, let's set the maximum speed, maximum acceleration, kinematics, voltage constraint, and whether or not the paths are inverted.

``` Java
TrajectoryConfig config = new TrajectoryConfig(maxSpeed, maxAcceleration);
config.setKinematics(/* Your kinematics object */);
DifferentialDriveVoltageConstraint voltConstraint = new DifferentialDriveVoltageConstraint(/* Your SimpleMotorFeedForward Object */, /* Your kinematics object */, maximumVoltage);
// For the SimpleMotorFeedForward Object, construct a new object using the average of the kVolts, the average of the kV, and the average of the kA values.
config.addConstraint(voltConstraint);
config.setInverted(inverted);

List<Pose2d> waypoints = new List<Pose2d>();
/* Add your waypoints here, starting with the beginning of the path */
Trajectory trajectory = TrajectoryGenerator.generateTrajectory​(waypoints, config);
```

## Pathweaver Tool
We can use the Pathweaver tool to create waypoints and curves. You can find instructions for using the tool [here](https://docs.wpilib.org/en/stable/docs/software/wpilib-tools/pathweaver/creating-pathweaver-project.html).

## Following Pathweaver Paths
There are three steps to an autonomous path command:

1. Load the odometry so that the robot's position is at the start of the path.

2. Create a [`RamseteCommand`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj2/command/RamseteCommand.html) to follow the trajectory. The `RamseteCommand` takes the following arguments:

    -  The trajectory to follow.

    - A `Supplier<Pose2d>` that gives the current pose of the robot. See [here](https://www.geeksforgeeks.org/supplier-interface-in-java-with-examples/) if you are unfamiliar with Suppliers.

    - A [`RamseteController`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/controller/RamseteController.html) object.

    - Your kinematics object.

    - A `Biconsumer<Double, Double>` representing your command that drives the robot given left and right speeds in m/s. This will be your characterized drive method. See [here](https://www.geeksforgeeks.org/java-8-biconsumer-interface-in-java-with-examples/) if you are unfamiliar with Biconsumers.

    - The subsystems that will be required. This will probably just be the drivetrain.

3. Stop the robot.

It is highly recommended that you use the [`andThen()`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj2/command/Command.html#andThen(edu.wpi.first.wpilibj2.command.Command...)) method from the Command class to schedule these processes.

That's it! Pathweaver is a powerful and simple tool that lets you do incredible things. You could create a command group to turn on a feeder mechanism, drive a trajectory, align to a target, run a shooter mechanism, and drive to pick up more game elements.