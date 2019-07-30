## 15 Data Points
Did you know that [with just 15 data points, researchers were able to identify 99.98% of Americans](https://www.reddit.com/r/privacy/comments/cjbo37/using_15_data_points_researchers_can_identify/)? What 15 data points do you think can identify you?

In this exercise, we'll just focus on three of data points: name, age, and favorite ice cream flavor. 

Write a `Person` class that takes those three things arguments in its constructor. In addition, the `Person` class should have these methods:

* `introduction()` which prints out `Hi, my name is [name]!` where `[name]` is replaced by the person's name.
* `isOlder()` which takes a `Person` object as its argument, and returns a boolean representing whether or not this person is older than the person who was passed into this method.
* `isCompatible()` which takes a `Person` object as its argument, and returns a boolean representing whether or not the two people like the same ice cream flavor.

Another requirement is that the person's name, age, and favorite ice cream flavor must not be directly accessible by any code outside of the `Person` class.

??? example "Solution"
    ```java
    class Person {
      private String name;
      private int age;
      private String flavor;

      public Person(String name, int age, String flavor) {
        /*
          Note: when a class's variables and arguments in a method have the
          same name, you can distinguish them by using "this.variable" to 
          access the class's variable, as shown below.
        */
        this.name = name;
        this.age = age;
        this.flavor = flavor;
      }

      public void introduction() {
        System.out.println("Hi, my name is " + name + "!");
      }

      public boolean isOlder(Person p) {
        return age > p.age;
      }

      public boolean isCompatible(Person p) {
        return flavor.equals(p.flavor);
      }
    }
    ```

## Dive In! 🌊
This section covered a lot. If you're still unfamiliar with the content, I highly suggest reviewing what we covered and really understanding it, this stuff is essential. Again, don't be afraid to ask questions!

However, if you're pumped and ready to write robot code, that's also awesome! If you want to get a head start, you can check out [our robot code from last year here](https://github.com/DeepBlueRobotics/RobotCode2019). The Java code is in `Robot2019/src/main/java/frc/robot/`.