# Common Sensors

There are four types of sensors that are used commonly in FRC competitions: encoders, gyroscopes, accelerometers, and distance sensors.

## Encoders
Encoders (more specifically rotary encoders) are a class of sensors which measure the angular position of a motor. They are often used in measuring the distance travelled by components of the robot or for determining speed. There are two types of encoders we will be focusing on: quadrature encoders and analog encoders.

Analog encoders report their readings as a continuous signal. An example of an analog encoder is the [ 
MA3 Absolute Encoder](https://www.andymark.com/products/ma3-absolute-encoder-with-cable), as shown in the image below. For clarification, an absolute encoder keeps a record of its position even after the encoder has lost power. The MA3 reports degree rotations of its associated shaft as voltage signal between 0 and 5 volts. Thus, a 180 degree rotation would, theoretically, correspond to an output of 2.5 volts. In practice, there may be small changes to the voltage reported by the MA3, perhaps due to magnetic field effect. As a result, a 180 degree rotation of the shaft might cause an analog encoder to report 2.47 volts or ~177 degrees. Analog encoders are much simpler than quadrature encoders, however, *their outputs are sensitive to physical phenomena*.

![MA3 Analog Encoder](https://www.usdigital.com/assets/images/galleries2/ma3_webproduct_01.jpg)

Quadrature encoders are incremental, meaning that they measure a certain number of ticks elapsed from a known position. You may be already familiar with quadrature encoders since Team 199 uses them extensively. Quadrature encoders are digital, meaning that there are a finite number of values their output can take; in this case, each channel (more on this later) reports either a 0 or a 1.

![Quadrature Encoder](https://docs.wpilib.org/en/stable/_images/encoding-direction1.png)

Quadrature encoders have two channels: an A channel and a B channel. The two are identical with the exception that the B channel has been phase shifted (a.k.a shifted to the right) by 90 degrees. When a motor rotates, it spins a code disc (shown below for an optical encoder), creating two sets of square waves. By counting the number of pulses, consisting of a rising-edge (0 -> 1) and a falling edge (1 -> 0), and dividing by the pulses-per-revolution (PPR), on one of the channels, we can determine the number of revolutions in a given period. The direction of rotation depends on the channel which detects a rising-edge first. If the A channel detects a rising-edge first, we say that A "leads" B and the motor is rotating clockwise; likewise, if B leads A, the motor is rotating counter-clockwise.

![Code Disc](https://upload.wikimedia.org/wikipedia/commons/thumb/1/1e/Incremental_directional_encoder.gif/220px-Incremental_directional_encoder.gif)

Here is a list of encoder terminology:

- Analog: reports outputs as a continuous signal.

- Absolute: retains position even after power is lost.

- Optical: shines light at the code disc - if light reaches a light sensor behind the disc, the signal turns on, otherwise it is off.

- Magnetic: uses a phenomenon known as the [Hall Effect](https://en.wikipedia.org/wiki/Hall_effect_sensor) to measure disturbances in a magnetic field.

- EPR: edges-per-revolution; the total number of falling and rising edges per revolution.

- Full period: four edges across the A and B channels.

- PPR: pulses-per-revolution.

- CPR: cycles-per-revolution; the same thing as the number of full periods per revolution.

## Gyroscopes
Gyroscopes measure the rotation of components on the robot. Usually, when working with a gyro, you will be using the nav-X gyro onboard the RoboRIO, shown below. In the bottom-left corner of the image, you can see the coordinate system used.

![nav-X](https://andymark-weblinc.netdna-ssl.com/product_images/navx-mxp-robotics-navigation-sensor/5bd3589f61a10d293f9646ad/zoom.jpg?c=1540577439)

Here is a clearer image of the nav-X coordinate system:

![coordinates](https://i1.wp.com/pdocs.kauailabs.com/navx-mxp/wp-content/uploads/2015/06/TriAxis.png?resize=300%2C256&ssl=1)

You can declare a nav-X gyro using the `AHRS()` class from Kauai Labs. Be sure to call the `.reset()` method when initializing!

## Accelerometers
Accelerometers measure - you guessed it - acceleration! Accelerometers with three axes can also measure rotation like a gyroscope. In fact, the nav-X can also measure the acceleration of the robot and compass heading.

You can declare an accelerometer using the `AHRS()` class or the `BuiltInAccelerometer()` class, as examples.

## Distance Sensors
