Unfortunately, not all JavaDocs are contained in one singular documentation. You may have to look in different documentations to find what you are looking for.
Here is a general outline of where stuff might be:

### [Java 11 Javadocs](https://docs.oracle.com/en/java/javase/11/docs/api/index.html)

- Regular Java code that is not unique to robots
- ArrayLists
- Wrapper Classes (Integer, Double, etc)

### [WPILIB Javadocs](https://github.wpilib.org/allwpilib/docs/release/java/index.html)

- Anything concerning how the command based project runs, and Driver Station related classes
- Joystick
- SmartDashboard
- Commands
- Subsystems
- Command Schedular

### Common Vendor Libraries

- Motors, Motor Controllers, other hardware on the robot
- [Revrobotics](https://codedocs.revrobotics.com/java/com/revrobotics/package-summary.html)
    - Spark/Sparkmax motor controllers
    - Color Sensors
- [Phoenix](https://api.ctr-electronics.com/phoenix/release/java/index.html)
    - Talon motor controllers
- Generally if you do not know, ask around what vendor the motor is from
- You can also check in lib199 code the MotorControllerFactory class to see what class type is used when creating a certain motor controller
    - Looking at imports `import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;` you can tell this motor is a phoenix

### [Lib199](https://github.com/DeepBlueRobotics/lib199)
- Anything whose import has "lib199" in it
- This code is written by Carlmont students that is reused every year
- You cannot find documentation online
- We have a long video detailing all classes in [lib199](https://www.youtube.com/watch?v=RMme7byAOPY)
    - MotorControllerFactory
!!! unfinished 
    If you are reading this, remind one of the veteran programs to add timestamps to the lib199 video

### Others
- [Limelight](https://docs.limelightvision.io/en/latest/)
- Any version control commands is related to git
- Google "[insert name] frc javadoc" or "[insert name] frc documentation" and hope for the best
- Ask a programming veteran

