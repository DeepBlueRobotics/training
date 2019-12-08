Unit tests are for testing functionality of independent parts of the code. Ideally, code and tests should be written so that faulty code that is not being tested in an individual unit test would not affect the success of code that is being tested. When it is necessary to write unit tests, they should be written first, before writing the code that is being tested.

We use unit tests to ensure the functionality of advanced code that does complicated calculations, parsing, or other operations. We DO NOT test code that directly interfaces with external libraries such as WPILib. As a result, we organize our code so that the fancy calculations and such are separate from the more low level code, and those pieces of code are usually located in our `lib` directory.

Examples of code that we should write unit tests for:

- string parsing for command sequences written in text files
- drivetrain characterization calcuations
- motion profiling calculations
- vision math
- swerve drive code
- custom log file encoding and parsing 

Examples of code that we should not write unit tests for:

- basic drivetrain teleop code
- basic subsystem or command code
- PID code that directly uses motor controllers
