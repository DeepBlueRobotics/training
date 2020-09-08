There are three types of sensors that are used commonly in FRC competitions: encoders, gyroscopes, and accelerometers. This page tries to give a good overview, however, if you want to learn more, check out WPILib's documentation about [sensors for software](https://docs.wpilib.org/en/latest/docs/software/sensors/index.html) and [sensors for hardware](https://docs.wpilib.org/en/latest/docs/hardware/sensors/index.html).

## Encoders
Encoders (more specifically rotary encoders) are a class of sensors that measure the angular position of a motor. They are often used in measuring the distance traveled by components of the robot or for determining speed. There are two types of encoders we will be focusing on: quadrature encoders and analog encoders.

Analog encoders report their readings as a continuous signal. An example of an analog encoder is the [ 
MA3 Absolute Encoder](https://www.andymark.com/products/ma3-absolute-encoder-with-cable). Absolute encoders keep a record of their position even after the encoder has lost power. The MA3 reports degree rotations of its associated shaft as a voltage signal between 0 and 5 volts. In practice, there may be small changes to the voltage reported by the MA3, perhaps due to magnetic field effects. As a result, a 180-degree rotation of the shaft might cause an analog encoder to report 2.47 volts (~177 degrees) instead of 2.5 volts. Analog encoders are much simpler than quadrature encoders, however, their outputs are much more sensitive to physical phenomena.

Quadrature encoders are incremental, meaning that they measure a certain number of ticks elapsed from a known position. You may be already familiar with quadrature encoders since Team 199 uses them frequently. Quadrature encoders are digital, meaning that there are a finite number of values their output can take; in this case, each channel reports either a 0 or a 1.

<a title="Sagsaw at English Wikipedia / Public domain" href="https://commons.wikimedia.org/wiki/File:Quadrature_Diagram.svg"><img width="512" alt="Quadrature Diagram" src="https://upload.wikimedia.org/wikipedia/commons/thumb/6/68/Quadrature_Diagram.svg/512px-Quadrature_Diagram.svg.png"></a>

<a href="https://commons.wikimedia.org/wiki/File:Quadrature_Diagram.svg" title="via Wikimedia Commons">Sagsaw at English Wikipedia</a> / Public domain

Quadrature encoders have two channels: an A channel and a B channel. The two are identical except that the B channel has been phase-shifted by 90 degrees. When a motor rotates, it spins a code disc (shown below for an optical encoder), creating two sets of square waves. By counting the number of pulses, consisting of a rising-edge (0 -> 1) and a falling edge (1 -> 0), and dividing by the pulses-per-revolution (PPR), on one of the channels, we can determine the number of revolutions in a given period. The direction of rotation depends on the channel which detects a rising-edge first. If the A channel detects a rising-edge first, we say that A "leads" B and the motor is rotating clockwise; likewise, if B leads A, the motor is rotating counter-clockwise.

<a title="Sidehack at English Wikibooks / Public domain" href="https://commons.wikimedia.org/wiki/File:Incremental_directional_encoder.gif"><img width="256" alt="Incremental directional encoder" src="https://upload.wikimedia.org/wikipedia/commons/1/1e/Incremental_directional_encoder.gif"></a>

<a href="https://commons.wikimedia.org/wiki/File:Incremental_directional_encoder.gif" title="via Wikimedia Commons">Sidehack at English Wikibooks</a> / Public domain

Here is a list of encoder terminology:

- Analog: reports outputs as a continuous signal.

- Absolute: retains position even after power is lost.

- Quadrature: consists of two channels, A and B, outputs a binary signal, reports ticks relative to some initial position.

- Optical: shines light at the code disc - if light reaches a light sensor behind the disc, the signal turns on, otherwise it is off.

- Magnetic: uses a phenomenon known as the [Hall Effect](https://en.wikipedia.org/wiki/Hall_effect_sensor) to measure disturbances in a magnetic field.

- EPR: edges-per-revolution - the total number of falling and rising edges per revolution.

- Full period: four edges across the A and B channels.

- PPR: pulses-per-revolution.

- CPR: cycles-per-revolution - the same thing as the number of full periods per revolution.

## Gyroscopes
Gyroscopes measure the rotation of components on the robot. Usually, when working with a gyro, you will be using the navX gyro onboard the RobRIO. You can find information about the navX on their [website](https://pdocs.kauailabs.com/navx-mxp/). One important section I will highlight is the [terminology](https://pdocs.kauailabs.com/navx-mxp/guidance/terminology/) section, which explains what terms such as "fused heading" and "yaw" mean and also defines the coordinate system.

You can declare a navX gyro using the [`AHRS()`](https://www.kauailabs.com/public_files/navx-mxp/apidocs/java/com/kauailabs/navx/frc/AHRS.html) class from Kauai Labs. You may need to call your gyroscope's `zeroYaw()` depending on how much of an impact sensor drift will have. See [here](https://www.kauailabs.com/support/navx-mxp/kb/faq.php?id=7) for information about dealing with sensor drift. You might also want to look at instructions for [calibrating the navX](https://pdocs.kauailabs.com/navx-mxp/?page_id=188)

## Accelerometers
Accelerometers measure - you guessed it - acceleration! Lucky for us, the navX can also measure the acceleration of the robot and compass heading. Often, we declare an accelerometer using the `AHRS()` class or the [`BuiltInAccelerometer()`](https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/BuiltInAccelerometer.html) class.
