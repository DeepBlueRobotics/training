Congratulations, you're finished with official training! Now, it's up to you to explore on your own. Here's a few topics you can learn about along with some resources for each, or you can choose something that's not on here. It's up to you now to proactively learn, ask questions, find answers, and write code. Have fun!

## PID (medium difficulty)
How do you get the robot to move a certain distance? Certainly, you can experimentally find the amount of time and power you need to run to get it to a distance, but that is not going to work for a variable distance. PID is a method of controlling a robot's movement based on its distance from the target, its speed, and the time elapsed. If tuned correctly, you can get pretty great results.

If you want to understand how PID works mathematically, you'll need to know basic calculus, but it's not necessary for writing and using PID code.

Resources:

- [PID Control - FRC Programming Done Right](https://frc-pdr.readthedocs.io/en/latest/control/pid_control.html)
- [Testing and Tuning PID Loops - Official WPILib docs](https://docs.wpilib.org/en/latest/docs/software/wpilib-tools/shuffleboard/advanced-usage/shuffleboard-tuning-pid.html)

## Motion Profiling (hard difficulty)
One problem with PID is that moving and turning are independent actions, effectively limiting your robot to either moving in a straight line or turning around its center at any given moment. Motion profiling is a solution to that, allowing the robot to move around faster and more elegantly. It is highly suggested that you develop a working PID system first before jumping into motion profiling.

Resources:

- [Motion Planning & Control in FRC - Team 254](https://youtu.be/8319J1BEHwM)
- [Jaci's Pathfinder](https://github.com/JacisNonsense/Pathfinder)

## Computer Vision (medium difficulty)
In every FRC game in recent memory, there are strips of retroreflective tapes paced in strategic places on the field. We can use vision to track these strips of tape and effectively calculate where we are relative to them. This helps with accuracy during an autonomous mode or auto-alignment code.

We have cameras, green light rings, Raspberry Pis, a Jetson TX1, and even a Limelight for us to play with and use for vision processing. I recommend starting with the Limelight because it requires significantly less setup and tweaking to get started with but isn't necessarily less powerful.

Resources:

- [Integrating Computer Vision with Motion Control - Team 254](https://www.team254.com/documents/vision-control/)
- [Vision - FRC Programming Done Right](https://frc-pdr.readthedocs.io/en/latest/vision/introduction.html)
- [VIsion Processing - Official WPILib docs](https://docs.wpilib.org/en/latest/docs/software/vision-processing/index.html)
- [Limelight Documentation](http://docs.limelightvision.io/en/latest/)

## Drivetrain Characterization (hard difficulty)
PID is honestly pretty finicky. Wouldn't it be great if there was a more scientific solution that doesn't involve constant trial and error? That's where drivetrain characterization comes in. The below paper shows that just by programmatically driving the robot around a bit, you can generate data that can mostly eliminate the need for tuning PID. This characterization can also applied to other mechanisms such as lifts, which operate under pretty similar physics.

Resources:

- [Paper: FRC Drivetrain Characterization - Chief Delphi](https://www.chiefdelphi.com/t/paper-frc-drivetrain-characterization/160915)

## LEDs (easy difficulty)
How do we make our robot look cool? Add RGB light strips of course! They can also serve practical uses by showing the drive team what state the robot is in via its colors. 

We have a small stash of RGB LEDs, along with an Arduino if that is needed. Make our robot look beautiful!

Resources:

- [Lights.java - Team 199 Robot Code 2019](https://github.com/DeepBlueRobotics/RobotCode2019/blob/dev/Robot2019/src/main/java/frc/robot/subsystems/Lights.java)
- Stephen Abbas (he wrote the above code along with the complementary Arduino code which I cannot find)
