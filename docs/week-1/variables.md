## Using Variables

Let's look at a slightly modified version of our previous Hello World program.

_HelloWorld.java_
```java
public class HelloWorld {
  public static void main(String[] args) {
    String hello = "Hello world!";
    System.out.println(hello);
  }
}
```

If you were to run this program, you'd get the exact same thing as before. But try changing `Hello world!` to something else and run the program again. What happens?

`hello` is what's called a **variable**, which are things that store data in a piece of code. In this case, the variable `hello` stores `"Hello world!"`. 

Let's walk through how we use variables. On line 3, we declare (aka create) the variable `hello` as a `String` type and **initialize** it to the value `"Hello world!"`. (We'll talk about `String` and other variable types on the next page.) And then on line 4, we access the data that is stored in `hello` and print that data out. 

## Changing variables

In addition to initializing and accessing variables, you can also change the values of already-initialized variables. For example, try adding this line in between the initialization statement and the print statement:

_HelloWorld.java_
```java hl_lines="4"
public class HelloWorld {
  public static void main(String[] args) {
    String hello = "Hello world!";
    hello = "water game 2020";
    System.out.println(hello);
  }
}
```
When you run the program again, `water game 2020` prints out instead of `Hello world!` because we changed the value of the variable `hello`.

## Setting variables to other variables

And finally, you can set variables to the values of other variables.

_HelloWorld.java_
```java hl_lines="4"
public class HelloWorld {
  public static void main(String[] args) {
    String hello = "Hello world!";
    String hello2 = hello;
    System.out.println(hello2);
  }
}
```