So now we have the code, how do we actually run it and test?

## Deploying and Building Code

Connect to the RoboRIO of the practice board with a USB cable or connect to its radio. Afterwards, click on the WPILib logo on the top right of VSCode which looks like this: ![wpilib](https://avatars1.githubusercontent.com/u/19267233?s=400&v=4){: style="height:25px;width:25px"} or press `Ctrl-Shift-P` to pull up command palette, and then choose "Deploy Robot Code". It should take a minute to deploy the code before displaying "Build Successful" in green. 

You can also build your code to see if your code compiles correctly without having to connect to a RoboRIO. Just do the steps above and instead of "Deploy Robot Code", choose "Build Robot Code".
You may see other options such as "Test", "Simulate", etc. We will cover those later.

Deploying code to the RoboRIO only works on a Windows computer; however, you can still build your code to see if it compiles correctly before moving onto a Windows computer to complete this part.

!!! note
    For more advanced programmers, the commands to deploy the robot code are just gradle tasks. You can emulate building and deploying by calling certain gradle commands listed [here](https://docs.wpilib.org/en/stable/docs/software/advanced-gradlerio/gradlew-tasks.html).

## Driver Station

This is the actual application on the driver station computer the controls the robot and reports data from it. WPILib provides a [general overview of everything](https://docs.wpilib.org/en/stable/docs/software/driverstation/driver-station.html) on the driver station, so read that. Here we will provide a general summary of what you need to know.

### Operation Tab
Here you control the robot mode (teleop, autonomous, etc) and can enable/disable. It also displays the status pane which shows PC battery, robot battery, and other important details.

### Diagnostics Tab
The main thing here is that this tab is used to restart the roboRIO or the robot code.

### USB Devices Tab
This is where you can configure the ports of your joysticks and other controllers on the driver station.

### Messages Tab
Displays error and warning messages.

## Debugging
We will not cover cases where the code fails to build, since this usually means there is a syntax error which can be easy to fix. The hardest bugs to fix are the ones where the program builds and deploys, but the robot does not do the exact task you wanted it to do. Sometimes it is either a programming or a sen-act problem.

### Code crashes when running
If your robot suddenly stops running while running the code, that can mean the code crashed. You can check [driver station logs](https://docs.wpilib.org/en/stable/docs/software/driverstation/driver-station-log-viewer.html) to see if there is a stack trace. Driver station logs also include a lot of information on events and other graphs.
Common errors that crash the code include:

- Null pointer exceptions
- Index out of bounds of array

There are also configurations set so that if a motor gets too hot, the motor will stop running. There is similar behavior for all sen-act components, whether it be with temperature or voltage. You can also check for those in driver station logs.

### Wrong robot behavior (SmartDashboard/ShuffleBoard & OutlineViewer)
However, just looking at driver logs may not help. What if the code does not crash at all, but the behavior is wrong? There is a chance that the data may be wrong, so you can check what data is being read in real-time using Shuffleboard/Smartdashboard.
WPILib provides the SmartDashboard class which you can use to input and ouput data. They also provide two articles on a general introduction to shuffleboard, so read [the tour](https://docs.wpilib.org/en/stable/docs/software/dashboards/shuffleboard/getting-started/shuffleboard-tour.html) and [how to display data](https://docs.wpilib.org/en/stable/docs/software/dashboards/shuffleboard/getting-started/shuffleboard-displaying-data.html).

Generally in order to initialize a "key" on the SmartDasboard, we do
`SmartDashboard.putNumber("key name", [insert default value]);`

In order to get whatever value is assiociated with the key, we do
`SmartDashboard.getNumber("key name);`

!!! warning
    The method `putNumber(key, val)` will replace whatever value is in ShuffleBoard with the `val`. If you have this in a `periodic()` method, then the value will constantly be replaced and you will not be able to change the value via ShuffleBoard.

`putNumber` is not the only method that can be ultilized. [Here](https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/wpilibj/smartdashboard/SmartDashboard.html) is SmartDashboard's javadoc which shows all the data that can be stored.

One significant method is `putData(Sendable object)`. Subsystems and commands implement the `Sendable` interface, so you can put an entire subsystem as one block of data onto Shuffleboard. Some lib199 classes also implement the `Sendable` interface, so you can also use `putData()` for those classes. You do not have to use a bunch of `putNumber()` or `getNumber()` methods for data, all the data is thrown into a nice box for convenience. For more details, see [this](https://docs.wpilib.org/en/stable/docs/software/telemetry/robot-telemetry-with-sendable.html).

You can also use [Outline Viewer](https://docs.wpilib.org/en/stable/docs/software/wpilib-tools/outlineviewer/index.html?highlight=outlineviewer) to see debugger values. It has less features than Shuffleboard, but is more compact and generally easier to use.

### Random glitches unrelated to code
Sometimes, the application may just randomly break and glitch out. Driver station is known to be glitchy sometimes, so the first step is always to restart your program. Restart robot code, restart roboRIO, restart driver station, etc.

### Common "behavior" bugs
Generally, you will want to replicate the behavior that is causing the error. This is why a lot of testing is required to have effective code. Once you know a specific condition generates a certain erroneous behavior, you can look at how that condition causes a bug and fix it.

Here are some bugs that might fix your issue:

- Joystick mounting methods `whenPressed()` and `whileHeld()` mean very different things!
- A command does not end properly
- You swapped some variables (you would notice this through Shuffleboard)

A lot of the times, the motor controller can get unplugged or broken. In that case blame sen-act. It is also helpful to know what the LED lights on a motor means, which you can find online.

Obviously, this list is not exhuastive and there could be numerous things that is causing your code to not work. It just takes a ton of practice to be able to notice bugs and how to fix them.