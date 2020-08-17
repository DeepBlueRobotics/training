One advantage of a characterized drivetrain is that it more effectively drives the robot to a precise location. This makes autonomous pathing a far more viable option. All we need to do is tell the robot a specific path to follow and the robot will drive the path pretty accurately.

But how do we specify these paths? We use the ```Trajectory``` class in conjunction with WPILib's Pathweaver tool.

Let's write some autonomous code!

## Trajectories
The ```Trajectory``` class computes the optimal path the robot should follow given a list of states. Each state contains information such as the time passed, velocity, acceleration, pose, and amount of curvature. Luckily for us, another class exists that helps create a trajectory given a list of points. This class is known as the ```TrajectoryGenerator``` and there are many ways to generate a trajectory using this class. For our purposes, we will be using the ```generateTrajectory()``` method using a list of waypoints and a ```TrajectoryConfig``` object.

For the ```TrajectoryConfig```, there are several constraints we can add. For now, lets set the maximum speed, maximum acceleration, kinematics, voltage constraint, and whether or not the paths are inverted.

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
We can use the pathweaver tool to create waypoints and bend the paths between these waypoints to create curves.

To start the Pathweaver tool, go to the top right corner of VS code and press the three dots. Then click the Start Tool button and from the options, select Pathweaver.

Next, select the project directory and type in the relevant parameters. This will include the game name, length measurement unit, max velocity, max acceleration, and wheel base (the distance between the centers of two opposite wheels). Then, hit Create Project. You should see the following interface:

![Pathweaver](https://docs.wpilib.org/en/stable/_images/pathweaver-4.png)

In the top-left corner you will see the field. Here is where you can add new waypoints and change the shape of trajectories. The properties of the selected waypoint - X, Y, Tangent X, and Tangent Y (note that the magnitude of the tangent line doesn't matter, only the direction) - are shown at the bottom. Path groups (collections of paths used for visualization) are shown in the top right corner and paths are shown in the bottom right corner.

You can select new paths by clicking on the desired path in the list of paths. You can create or remove paths by using the + and - buttons in the same region.

Each waypoint looks like the head of an arrow and the direction of the arrow is determined by its tangent line. Clicking and dragging on a waypoint moves it to a new location. New waypoints can only be created between existing waypoints and are created by clicking on a path between two waypoints.

When you are done, save the paths and close the tool. It is not necessary to build the paths.

Get creative with your paths!

## Following Pathweaver Paths
Telling the robot to follow a path is simple. There are three steps to an autonomous path command:

1. Load the odometry so that the robot's position is at the start of the path.

2. Create a ```RamseteCommand``` to follow the trajectory. The ```RamseteCommand``` takes the following arguments:

    -  The trajectory to follow.

    - A ```Supplier<Pose2d>``` that gives the current pose of the robot.

    - A ```RamseteController``` object.

    - Your kinematics object.

    - A ```Biconsumer<Double, Double>``` representing your command that drives the robot given left and right speeds in m/s. This will be your characterized drive method.

    - The subsystems that will be required. This will most likely only be the drivetrain subsystem.

3. Stop the robot.

It is highly recommended that you use the ```andThen()``` method from the Command class to schedule these processes.

That is all to it! Pathweaver is a powerful and simple tool that lets you do incredible things. You could create a command group to turn on a feeder mechanism, drive a trajectory, align to a target, run a shooter mechanism, and drive to pick up more game elements.