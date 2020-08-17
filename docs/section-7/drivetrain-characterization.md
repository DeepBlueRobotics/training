## Introduction
If you have driven a robot around before (it's okay if you haven't), you might have noticed that you have to move the joysticks forward a fair amount in order to get the robot moving. Why is that?

Well, the motors have to overcome three primary obstacles: friction between the wheels and the surface, back-EMF, and motor resistance. In FRC competitions, the surface is a carpet with a static friction of about 1.0, which is considerable and should be accounted for in moving the drivetrain.

And Back-EMF? Well, EMF (electromotive force) is the force which causes electrons to move in circuits and is caused by a difference in electrical potential (voltage). When electricity passes through a DC motor, it goes through a copper coil. It is known that a charged copper coil creates a magnetic field and it is this magnetic field which spins the motor. However, this magnetic field also creates an electromotive force moving against the current flow. As a result, the motor will spin at a speed less than what was desired.

![DC Motor](https://blog.seeedstudio.com/wp-content/uploads/2019/04/DC_motor_rotor_labels-1030x773.jpg)

How do we account for friction, back-EMF, and motor resistance? We use a technique known as Drivetrain Characterization. In this section, we will develop the Drivetrain Characterization formula along with various tests to determine the constants required.

Let's go!

## Developing the Drivetrain Characterization Formula
The voltage we should supply to the motors should be enough to counteract back-EMF, friction, but also spin the wheels at the desired speed. Let's define the voltage we apply as \(V_{app}\). Then:

\[V_{app} = V_{emf} + V_{windings}\]

Where \(V_{emf}\) is the voltage due to back-emf and \(V_{windings}\) is the voltage across the copper windings of the motor.

The voltage due to back-emf is proportional to the angular speed of the wheels. Therefore it is also proportional to the translational speed of the robot, or \(V_{emf} = k_v * \text{velocity}\). The voltage in the windings causes the wheels to experience torque, which is proportional to the acceleration of the wheels and therefore the robot, meaning \(V_{windings} = k_a * \text{acceleration}\). Finally, we need to account for static friction. Since the force needed to overcome static friction is constant, all that needs to be done is add a third constant - let's call it \(k_{volt}\). Our new formula becomes:

\[V_{app} = k_{volt} + k_v * \text{velocity} + k_a * \text{acceleration}\]

!!! note
    The techniques described in this section can be used to determine formulas for efficiently controlling subsystems other than drivetrains, such as robot arms, elevators, or flywheel shooters. In the case of other subsytems, determine the forces going against your system and include them in your equation. In most cases, the equation will take the form of some constant plus a velocity term and an acceleration term. However, some systems may need to add additional terms to counteract gravity, such as in elevator control
    
    \[V_{app} = k_g + k_{volt} + k_v * \text{velocity} + k_a * \text{acceleration}\] 
    
    or arm control 
    
    \[V_{app} = k_{volt} + k_{g} * \cos({\text{angular position}}) + k_v * \text{angular velocity} + k_a * \text{angular acceleration}\].

This is the drivetrain characterization formula. In practice, however, we apply the drivetrain characterization formula to either each side of the drivetrain and each direction. That means we need equations for each side of the drivetrain (left/right) and for each direction (wheels moving forward/backward).

\[V_{fl app} = k_{fl volt} + k_{fl v} * \text{fl velocity} + k_{fl a} * \text{fl acceleration}\]

\[V_{fr app} = k_{fr volt} + k_{fr v} * \text{fl velocity} + k_{fr a} * \text{fr acceleration}\]

\[V_{bl app} = k_{bl volt} + k_{bl v} * \text{bl velocity} + k_{bl a} * \text{bl acceleration}\]

\[V_{fb app} = k_{br volt} + k_{br v} * \text{fl velocity} + k_{br a} * \text{br acceleration}\]

That's a lot of constants! How do we find them?

## Determining the Constants
To determine the drivetrain characterization constants \(k_{volt}\), \(k_{v}\), and \(k_{a}\), we must determine scenarios in which we isolate each dependent variable and gather data. We can then analyze the data using regression analysis (determining the best fit line, or in this case plane).

A situation in which velocity changes, but acceleration is constant, is one in which the applied voltage increases linearly. A situation in which acceleration changes, but velocity is constant, is one in which the applied voltage jumps from a small value to a large value in a short period of time; we call this behavior "stepwise".

For the tests, we record the voltage applied to each side of the drivetrain and measure the velocity and acceleration for each side. As well, we repeat each test twice: one for moving straight forwards, another for moving straight backwards. Once we have collected the data, we can perform ordinary least squares regression to determine the constants.

This sounds like a lot of work, but WPILib has a tool that greatly simplifies our work.

## FRC-Characterization Tool
The FRC characterization tool is a python program used to determine characterization constants.

### Installation
Make sure that Python version 1.7 is installed on the computer that will be connecting to the robot. To install the tool, type:

``` pip install frc-characterization ```

Make sure the path to your pip executable is in your PATH environment variable. If not, add it to PATH or replace pip with the link to the pip executable.

### Creating a New Project

Simply type frc-characterization and you will be greeted with a text interface where you can select a characterization mode.

Once a graphical interface is available, create a new characterization project (or select a pre-existing project), by hitting the Select Project Location button respectively. In addition, you will need to select the project type based on the motors used. The options include:

- Simple: uses encoders plugged into the RIO’s DIO ports, and measure voltage with the PDP.

- Talon: uses encoders plugged into a Talon SRX, and measure voltage with the Talon.

- SparkMax (Brushed): uses encoders plugged into a SparkMax motor controller with a brushed motor, and measure voltage with the Spark.

- SparkMax (Brushless/NEO): uses the internal encoder of a NEO motor, and measure voltage with the SparkMax motor controller.

You will also need to configure the project parameters in the text editor. Don't forget to save your changes with the Save Config button! When ready, hit the Generate Project button to create a project and, when connected to the robot, hit the Deploy Project code to deploy the robot.

### Gathering Data

When you are ready to gather data, and your robot is in a suitable testing location (at least a 10-inch square, optimally 20 inches), hit the Launch Data Logger button. You will see the following window pop-up:

![data logger](https://docs.wpilib.org/en/stable/_images/data-logger-gui.png)

Next, change the team number to 199 and hit to Connect to Robot. In addition, you can change the quasistatic ramp rate (rate of change of voltage in the linear test) or the dynamic step voltage (the value to step to in the stepwise test). When ready, hit the buttons on the left to start the tests. A window will pop up telling you how to continue. You will have to use the driverstation interface to disable, or press enter. The quasistatic tests should be run until the robot reaches the end of the testing area (make sure to stop slightly earlier so that the robot doesn't crash) and the dynamic tests should run for no more than 2 seconds.

Here is an example of what to expect:

<iframe width="716" height="403" src="https://www.youtube.com/embed/FN2xqoB1sfU" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>

When you are finished, make sure to save your data with the Save Data button.

### Computing the Constants

Launch the Data Analyzer using the Launch Data Analyzer button on the original window. There should be a new pop-up where you can select the data file that you created in the previous section.

Before you hit the Analyze Data Button, make sure that you change your wheel diameter in the top-right corner to use the correct units and is the right value. Also, make sure that you change the subset value so that you can gather data for forward left, forward right, etc. Then, hit analyze data.

Make sure to record the kS (\(k_{volts})\), kV, and kA values for each side and each direction. These values can be found in the top-left corner of the Feedforward Analysis section. As well, you will need to record the kP and (depending on application) kD for the left and right sides. These values can be found in the bottom-right corner of the Feedback Analysis section

## Implementing Drivetrain Characterization

From here, implementation is not very difficult, however, the code is kind of dense.

You will need to initialize six objects: 1 ```SimpleMotorFeedforward``` objects for each side and direction (4 total), and 1 ```PIDController``` for each side. The constructor for each ```SimpleMotorFeedforward``` will require the kS, kV, and kA we found in the previous section and each ```PID Controller``` will require the kP (and possibly kD) from the previous section.

Our characterized driving code will require two parameters, left and right, the desired speed of the wheels in m/s for the left and right sides. The code will then supply the motors with the correct voltage. Below are the instructions for creating our characterized driving code:

1. For the left and right sides, determine the desired acceleration of the robot by finding the change in velocity between our desired velocity and our current velocity and dividing by the change in time between the two measurements.

2. (Optional) Clip the acceleration at some maximum value. Note that the maximum acceleration the robot can achieve without the wheel slipping is \(\mu_s g\) where \(\mu_s\) is the coefficient of static friction and \(g\) is the gravitational acceleration.

3. Apply the drivetrain characterization formula for each side and direction using the ```SimpleMotorFeedforward.calculate()``` method.

4. Determine the adjustment value for each side using the ```PIDController.calculate()``` method.

5. If the robot will be moving forward on the left side, the desired voltage is the sum of the left PID output and the forward left FF output; otherwise, the voltage is the sum of the left PID output and the backward left FF output; otherwise, the voltage. Repeat for the right side.

6. Divide by the maximum voltage and supply the final results to your drivetrain's ```tankDrive()``` method.

You should now have everything you need to implement your own code for a characterized drivetrain.