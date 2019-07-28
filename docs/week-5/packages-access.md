## Packages
First, we need to talk about packages.

Java projects and libraries are organized in the form of packages. You denote what package a piece of Java code is part of by using the `package` keyword on the top of the file, and the package name usually corresponds to the directory structure. Let's add a package to our Cat class.

_src/org/carlmontrobotics/training/Cat.java_
```java hl_lines="1"
package org.carlmontrobotics.training;

class Cat {
  private static String kingdom = "Animalia";

  public int lives = 9;
  public String name;

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

Now, if you ever want to use the `Cat` class in another piece of Java code, all you need to do is import it!
```java
import org.carlmontrobotics.training.Cat;
```

## Access Modifiers
You've probably noticed the `public` or `private` keywords in front of some of our methods and variables, and we've told you to ignore those for the time being. Well now is the time to talk about them! 

These are called **access modifiers**, and there's four of them: `private`, `protected`, `public`, and the default modifier which is when you don't specify any of the three former ones. These modifiers affect what code can access each method or variable. 

Out of the four, we are only going to cover `public` and `private` since we use those the most.

### Public

Placing the `public` modifier in front of a method or variable allows any piece of code to access it, including code from other classes and other packages. 

In the above `Cat.java` example, `lives`, `name`, `bark()`, `getKingdom()`, and the constructor are all public, so they can be used by any piece of code with just an import.

### Private

Methods and variables marked with `private` can only be accessed by code in the same class.

For example, the String variable `kingdom` in `Cat.java` can only be accessed by code in the `Cat` class. Note that since `getKingdom()` is public and accesses `kingdom`, you can indirectly access the value of `kingdom`, but you cannot modify it. This follows the getter/setter paradigm you learned in Section 3. <!-- TODO: add link to section 3 -->

!!! info "Further Reading"
    If you would like to know more about access modifiers and how they're used, you should check out the respective page of the official Java tutorials: <https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html>