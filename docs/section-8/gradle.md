<style>sub{color:gray;}</style>
<style>green{color:#006600;}</style>
<style>red{color:#660000;}</style>
<style>big{font-size:20px;}</style>

# Building and Deploying
<sub><big> On your OWN computer! </big></sub>

Okay, don't go nuts, but you can build and deploy robot code from your terminal. You can't use the Driverstation software without... well... *using the Driverstation app,* but this can be extremely useful if you aren't coding in VSCode, or the driverstations are being wacky.

Thankfully, there's a file that makes all these things very easy for you.

## The gradlew file

Every EmptyProject20XX repository comes with a `gradlew` file, and by extension every RobotCode20XX will have it as well. Gradlew is an __executable__ file, which means you run it by using the `./` filepath prefix.

Running `./gradlew` without anything else actually just builds the Robot Code. It's that easy - exactly the same thing happens as when you're using VSCode.

!!! note
	If your terminal tells you `Permission Denied` when running the file, do `sudo chmod 777 ./gradlew`. This gives everyone permission to run the executable.

However, `./gradlew tasks` will give you the full (very big!) list of what you can do. But the only ones we care about are `./gradlew build` and `./gradlew deploy`. 

All of gradlew's things are equivalent to their VSCode counterparts. 

That's all there is to it.