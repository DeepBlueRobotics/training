## MysteryMath

We use the JUnit 4 library to write unit tests. We put our tests in the `src/test` folder of a robot project, and the folder structure should mirror the structure of the main code in `src/main`. In addition, test files should follow the naming convention `[class to be tested]Test.java`.

Clone the [`TestRobot`](https://github.com/DeepBlueRobotics/TestRobot) repo. We have a test written called `MysteryMathTest.java` in the `TestRobot` project.

Before you look in either `MysteryMath.java` or `MysteryMathTest.java`, open the `TestRobot` project in VS Code, click on the WPILib button, and select "Test Robot Code". 

Your output should be similar to this:
```
> Executing task: ./gradlew test   -Dorg.gradle.java.home="/home/kev/frc2019/jdk" <


> Task :test FAILED

org.team199.lib.MysteryMathTest > testFib PASSED

org.team199.lib.MysteryMathTest > testFour FAILED
    java.lang.AssertionError at MysteryMathTest.java:21

org.team199.lib.MysteryMathTest > testInverseAbsPositive FAILED
    java.lang.AssertionError at MysteryMathTest.java:33

3 tests completed, 2 failed

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':test'.
> There were failing tests. See the report at: file:///home/kev/dev/training/code/TestRobot/build/reports/tests/test/index.html

* Try:
Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output. Run with --scan to get full insights.

* Get more help at https://help.gradle.org

BUILD FAILED in 1s
4 actionable tasks: 3 executed, 1 up-to-date
```

Let's look at this output closely.

There's one test that ran successfully:
```hl_lines="3"
> Task :test FAILED

org.team199.lib.MysteryMathTest > testFib PASSED

org.team199.lib.MysteryMathTest > testFour FAILED
    java.lang.AssertionError at MysteryMathTest.java:21
```

And two tests that failed:
```hl_lines="3 6"
org.team199.lib.MysteryMathTest > testFib PASSED

org.team199.lib.MysteryMathTest > testFour FAILED
    java.lang.AssertionError at MysteryMathTest.java:21

org.team199.lib.MysteryMathTest > testInverseAbsPositive FAILED
    java.lang.AssertionError at MysteryMathTest.java:33

3 tests completed, 2 failed
```

Now look in the code, both the test class and the class being tested. Can you find the two errors? Hint: one of the problems is with the test itself, the other is with the `MysteryMath` code.

??? success "Click for answers"
    1. Line 21 of `MysteryMathTest.java`, in `testFour()`, should be asserting equals `MysteryMath.four()` to `4`, not `2`
    2. Line 25 of `MysteryMath.java`, in `inverseAbs()`, should absolute value the input if it's less than zero, not greater.

## MysteryMath2

Now, it's your time to write some tests. The `quadraticFormula()` method in `MysteryMath2.java` looks like it works, or does it? Write `MysteryMath2Test.java` to test the code with different coefficients. What inputs make the tests fail? Once you figure it out, you should also fix the code.

??? success "Click for answers"
    1. If your test inputs coefficients for an equation that has no solutions (ex: `x^2+x+1`), the program is going to throw an exception.
    2. To fix this, the `quadraticFormula()` method should first check if `b*b-4*a*c` is non-negative before doing the calculation. If it's negative, it should just return -1.

## More on Unit Tests
We've only used a very small set of Java unit testing capabilities thus far. You can read up on more of JUnit 4's capabilities in the [JUnit documentation](https://junit.org/junit4/javadoc/latest/index.html) (also found in the "Resources" section of this site). In addition to JUnit, we may also use:

- [Mockito](https://site.mockito.org/) - used to simulate other parts of the code that is being accessed, so that a unit test won't be impacted by how other parts of the code run
- [Hamcrest](http://hamcrest.org/JavaHamcrest/tutorial) - used to create more advanced match rules to test if classes have the correct state