## Packages
Java classes can be organized into packages to avoid conflicts between types with the same name, and to make it easier for programmers to find the types they want to use. You denote what package a class is part of by using the `package` keyword at the top of the file, and the package name usually corresponds to the directory structure. Let's put our Cat class in a package named `org.carlmontrobotics.training`.

_src/org/carlmontrobotics/training/Cat.java_
```java hl_lines="1"
package org.carlmontrobotics.training;

class Cat {
  static String kingdom = "Animalia";

  int lives = 9;
  String name;

  Cat(String n) {
    name = n;
  }

  void bark() {
    System.out.println("woof");
  }

  static String getKingdom() {
    return kingdom;
  }
}
```

Now, if you ever want to use the `Cat` class from code in a different package, you'll need either to use the full name `org.carlmontrobotics.training.Cat` everytime you use it or you'll need to **import** it so that you can use the shorter name `Cat`.
```java
import org.carlmontrobotics.training.Cat;
```

## Access Modifiers
By default, you can only access a class, its methods, or its variables from other classes in the same package. To allow access from outside the same package, the `public` keyword can be used. To prevent access from other classes in the same package, the `private` keyword is used. These keywords are called **access modifiers**.

Consider the following code:

_src/org/carlmontrobotics/training/Cat.java_
```java hl_lines="1"
package org.carlmontrobotics.training;

public class Cat {
  private static String kingdom = "Animalia";

  private int lives = 9;
  private String name;

  public Cat(String n) {
    name = n;
  }

  public void bark() {
    System.out.println("woof");
  }

  public static String getKingdom() {
    return kingdom;
  }
}
```

### Public

Placing the `public` modifier in front of a class, method, variable allows any piece of code to access it, including code from other classes and other packages. 

In the above `Cat.java` example, the `Cat` class, its constructor, and the methods `bark()` and `getKingdom()`, are all public, so they can be used by any piece of code with just an import.

### Private

Methods and variables marked with `private` can only be accessed by code in the same class.

For example, the variables `kingdom`, `lives`, and `name` in `Cat.java` can only be accessed by code in the `Cat` class. Note that since `getKingdom()` is public and accesses `kingdom`, you can indirectly access the value of `kingdom`, but you cannot modify it. This follows the getter/setter paradigm you learned in Section 3. <!-- TODO: add link to section 3 -->

!!! info "Further Reading"
    If you would like to know more about access modifiers and how they're used, you should check out the respective page of the official Java tutorials: <https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html>