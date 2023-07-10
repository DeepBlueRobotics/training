Oftentimes, we want to use our robots for outreach programs and have non-robotics students drive them. But how do we make sure that they don't accidentally break the robot? The answer: Safe Mode!

# What is Safe Mode
Safe mode is a state of the robot where joystick inputs are slowed and any controls we don't want non-robotics people to use are disabled. This often required writing a lot of very similar code every year, so we simplified it into the Lib199 Safe-Mode API, which is contained in the `org.carlmontrobotics.lib199.safeMode` package. There are two ways to enable/disable safe-mode 1) The "Safe Mode" Smart Dashboard value, and 2) The `enable()` and `disable()` functions in the `SafeMode` class.

# Checking Safe Mode State
The robot code can check the sate of safe-mode with the `SafeMode.isEnabled()` function. Additionally, the `SafeMode.onEnabled(Runnable)` and `SafeMode.onDisabled(Runnable)` functions allow functions to be run when the safe-mode state changes. For example:
``` Java
SafeMode.onEnabled(() -> {
    // Do something
});
```

# Constants
It can sometimes be helpful to change a value depending on whether safe-mode is enabled (max speed/setpoint/etc). For this, you can use `SafeMode.constant(normalValue, safeValue)`. This method returns a `Supplier`. To get the value, call the Supplier's `get()` method. This method will return the `safeValue` if safe-mode is enabled and `normalValue` otherwise.
!!! note
    There are actually 5 `Supplier` classes. They are very similar, but the class and method names are slightly different. They are `BooleanSupplier` (for `boolean`s), `DoubleSupplier` (for `double`s), `IntSupplier` (for `int`s), `LongSupplier` (for `long`s), and `Supplier` (for everything else).

# Joysticks
Another common occurrence is to try and adjust the raw joystick values from the driver. To do this, wrap your joystick object with the `SafeMode.makeSafe(GenericHID)` method. This method returns an object of the same type that's passed into it, so it works with all GenericHID subclasses. For example:
``` Java
XboxController controller = SafeMode.makeSafe(new XboxController(port));
```
The behavior of this controller in safe-mode can then be altered with other methods in the `SafeMode` class. Each one accepts either the joystick's port or the joystick object. An basic list is provided below:

`SafeMode.disableButton` - Disables the specified button when safe-mode is enabled
`SafeMode.disableAxis` - Disables the specified axis when safe-mode is enabled (so it always reads `0`)
`SafeMode.scaleAxis` - Scales the specified axis by an arbitrary factor when safe-mode is enabled
`SafeMode.disablePOV` - Disables a specific angle on a POV of the joystick. If no POV is provided, it will default to POV `0` (this is also the default for WPILib).

A full method list is available in the [SafeMode Class Javadoc](https://deepbluerobotics.github.io/lib199/org/carlmontrobotics/lib199/safeMode/SafeMode.html).

# Safe Commands
Finally, it is sometimes desireable to only run a command when safe-mode is enabled or, conversely, when safe-mode is disabled. For this there are 4 command classes provided: `SafeCommand`, `SafeExecuteBlockingCommand`, `UnsafeCommand`, and `UnsafeExecuteBlockingCommand`. The first two only run when safe-mode is enabled, and the latter two when safe-mode is disabled.

Unlike many other command classes, rather than being extended, these commands implement their functionality by having another command passed to their constructor. For the full reasoning behind this as well as some tips for getting around it, see [this PR comment](https://github.com/DeepBlueRobotics/lib199/pull/43#discussion_r1247247810).

## `SafeCommand`/`UnsafeCommand`
Most of the time, if you're using a command, this is what you want to use. If the command is scheduled while the code is in the incorrect safe-mode state, nothing will happen. Otherwise, the command works normally. If a safe-mode change makes it so the command should not be run, `end(true)` will be called on it, and the command will return `isFinished() = true` to `CommandScheduler`.

## `SafeExecuteBlockingCommand`/`UnsafeExecuteBlockingCommand`
Some commands may need to remain running even if they shouldn't execute logic (e.g. default commands). For this case, `SafeExecuteBlockingCommand` and `UnsafeExecuteBlockingCommand` should be used. They only block the command's `execute()` method. This allows the original command's `isFinished()` method to continue to determine the behavior of the command.
!!! warning
    Because these two commands do not block calls to `initialize()` or `end()`, they should not be used where that logic is important (such as with `InstantCommand`).
