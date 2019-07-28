## Classes

You've been using Java classes since week 1, with our first Hello World program, but what are classes, really?

**Classes** are Java's way of grouping variables, methods, and other classes together. 

For example, you may have a Cat class:

_Cat.java_
```java
class Cat {
  public int lives = 9;

  public void bark() {
    System.out.println("woof");
  }
}
```

## Objects

Instances of classes are called **Objects**.

For example, you may have a cat named Marshmallow. This is how you would create its object:
```java
Cat marshmallow = new Cat();
```
And with that Cat object, you can call methods in Cat and access variables in that Cat object:
```java
marshmallow.bark(); // "woof"
System.out.println(marshmallow.lives); // 9
```

## Constructors
Oftentimes, you may want to create an Object with a starting state, also called **initializing** an Object. To do that, you use what is called a **constructor**. Constructors are very similar to methods, and they are denoted by the name of the class they are part of.

Say we wanted to create a Cat object and set its name.

_Cat.java_
```java hl_lines="3 5 6 7"
class Cat {
  public int lives = 9;
  public String name;

  public Cat(String n) {
    name = n;
  }

  public void bark() {
    System.out.println("woof");
  }
}
```

And then we can create a Marshmallow object as such:
```java
Cat marshmallow = new Cat("Marshmallow");
```

Similar to methods, you can also have multiple constructors with different arguments for each:
```java hl_lines="9 10 11"
class Cat {
  public int lives = 9;
  public String name;

  public Cat(String n) {
    name = n;
  }

  public Cat() {
    name = "idk";
  }

  public void bark() {
    System.out.println("woof");
  }
}
```

## Static methods and variables
The methods and variables we've been using on this page have all been what are called **instance** methods and variables. That means that they are used with an instance of a class, AKA an Object. 

**Static** methods and variables on the other hand, are called directly from the class itself, and there is only one instance of them. They are denoted with the keyword `static` before them. 

Where have you already used the `static` keyword before? In your main method of course!
```java
public static void main(String[] args) {
```

Let's take a look at how we would use the `static` keyword. For example, we might want to store the animal kingdom that cats are from, but all cats are part of the same kingdom, so we can use a static variable in Cat:

_Cat.java_
```java hl_lines="2 15 16 17"
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

You can then call that method as you would an object's methods, but as a class:
```java
System.out.println(Cat.getKingdom()); // "Animalia"
```

!!! note
    Instance methods can call both instance and static methods, and can use both instance and static variables. 
    
    On the other hand, static methods can only access static variables and call static methods. That's why you always had to put `static` in front of methods you were calling from the main method.