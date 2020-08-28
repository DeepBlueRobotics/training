One advantage of a characterized drivetrain is that it more effectively drives the robot to a precise location. This makes autonomous pathing a far more viable option. All we need to do is tell the robot a specific path to follow and the robot will drive the path pretty accurately.

But how do we specify these paths? We use the `Trajectory` class in conjunction with WPILib's Pathweaver tool.

Let's write some autonomous code!

## Trajectories
The `Trajectory` class computes the optimal path the robot should follow given a list of states. Each state contains information such as the time passed, velocity, acceleration, pose, and amount of curvature. Luckily for us, another class exists that helps create a trajectory given a list of points. This class is known as the `TrajectoryGenerator` and there are many ways to generate a trajectory using this class. For our purposes, we will be using the `generateTrajectory()` method using a list of waypoints and a `TrajectoryConfig` object.

For the `TrajectoryConfig`, there are several constraints we can add. For now, lets set the maximum speed, maximum acceleration, kinematics, voltage constraint, and whether or not the paths are inverted.

```
TrajectoryConfig config = new TrajectoryConfig(maxSpeed, maxAcceleration);
config.setKinematics(/* Your kinematics object */);
DifferentialDriveVoltageConstraint voltConstraint = new DifferentialDriveVoltageConstraint(/* Your SimpleMotorFeedForward Object */, /* Your kinematics object */, maximumVoltage);
// For the SimpleMotorFeedForward Object, construct a new object using the average of the kVolts, the average of the kV, and the average of the kA values.
config.addConstraint(voltConstraint);
confif.setInverted(inverted);
```

The list of waypoints will be generated using the Pathweaver tool. The tool will create .csv files for each path. To convert the .csv elements to Pose2D objects, we can use the following code:

```
ArrayList<Pose2d> poses = new ArrayList<Pose2d>();

try {
    CSVParser csvParser = CSVFormat.DEFAULT.parse(new FileReader(file));
    double x, y, tanx, tany;
    Rotation2d rot;
    List<CSVRecord> records = csvParser.getRecords();

    for (int i = 1; i < records.size(); i++) {
        CSVRecord record = records.get(i);
        x = Double.parseDouble(record.get(0)) + initPos.getX();
        y = Double.parseDouble(record.get(1)) + initPos.getY();
        tanx = Double.parseDouble(record.get(2));
        tany = Double.parseDouble(record.get(3));
        rot = new Rotation2d(tanx, tany);
        if (isInverted) { rot = rot.rotateBy(new Rotation2d(Math.PI)); }
        poses.add(new Pose2d(x, y, rot));
    }
    csvParser.close();
} catch (FileNotFoundException e) {
    System.out.println("File named: " + file.getAbsolutePath() + " not found.");
    e.printStackTrace();
}
```

This code uses code from org.apache.commons.csv. You will need to add the following line to the dependencies section of build.gradle if not already present:

```compile group: 'org.apache.commons', name: 'commons-csv', version: '1.6' ```

## Pathweaver Tool
We can use the Pathweaver tool to create waypoints and bend the paths between these waypoints to create curves. You can find instructions for using the tool [here](https://docs.wpilib.org/en/stable/docs/software/wpilib-tools/pathweaver/creating-pathweaver-project.html).

I will add a few notes though. The length of the tangent line has _no effect_ on the trajectory. Only the direction of the tangent line matters. In addition, since Team 199 last used Pathweaver, the Build Paths button was not working. So, when you are done, save the paths and close the tool - don't build the paths (besides, the button will likely not work).

Get creative with your paths!

## Following Pathweaver Paths
Telling the robot to follow a path is simple. There are three steps to an autonomous path command:

1. Load the odometry so that the robot's position is at the start of the path.

2. Create a [`'RamseteCommand`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj2/command/RamseteCommand.html) to follow the trajectory. The `RamseteCommand` takes the following arguments:

    -  The trajectory to follow.

    - A `Supplier<Pose2d>` that gives the current pose of the robot. See [here](https://www.geeksforgeeks.org/supplier-interface-in-java-with-examples/) if you are unfamiliar with Suppliers.

    - A [`RamseteController`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/controller/RamseteController.html) object.

    - Your kinematics object.

    - A `Biconsumer<Double, Double>` representing your command that drives the robot given left and right speeds in m/s. This will be your characterized drive method. See [here](https://www.geeksforgeeks.org/java-8-biconsumer-interface-in-java-with-examples/) if you are unfamiliar with Biconsumers.

    - The subsystems that will be required. This will probably just be the drivetrain.

3. Stop the robot.

It is highly recommended that you use the [`andThen()`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj2/command/Command.html#andThen(edu.wpi.first.wpilibj2.command.Command...)) method from the Command class to schedule these processes.

That is all to it! Pathweaver is a powerful and simple tool that lets you do incredible things. You could create a command group to turn on a feeder mechanism, drive a trajectory, align to a target, run a shooter mechanism, and drive to pick up more game elements.