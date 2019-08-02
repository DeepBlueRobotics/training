##If/else statements

So far, all of our programs run in a boring line from top to bottom, but it would be helpful to have the computer do some thinking. For example, what if you wanted your "Hello World" program to give different greetings based on the time of day? 

Enter the **if** statement. It's not hard to guess what they do. 

_HelloWorld.java_ 
```java
public class HelloWorld {
    public static void main(String[] args) {
        int timeOfDay = 9;
        String morning = "Good morning, California!";
        String hello = "Hello world!";
        if (timeOfDay < 12) {
            System.out.println(morning);
        } else {
            System.out.println(hello);
        }
    }
}
```

What do you think this will print? Update your copy of `HelloWorld.java` and run it. 

Now try initializing `timeOfDay` to something greater than 12. What happens this time? Alternatively, what happens if you set the number in the parentheses next to `if` to something less than `timeOfDay`? 

That thing in the parentheses is a **boolean** called a **condition**. Every if statement has one, and if it evaluates to `true`, any code inside the curly brackets is run. If not, the code inside `else` is run. 

##Logical operators 

If you wanted to restrict the morning message to not show when it's technically nighttime, it wouldn't be logically wrong to write it like this: 

```java 
if (timeOfDay < 12) {
    if (timeOfDay > 5) {
        System.out.println("Good morning, California!");
    }
}
```

But since that takes up space and no one wants to write that much, we have a better way to do this. The `&&` operator groups two booleans as one bigger boolean that tests whether both are true. You can use it like this: 

```java
if (timeOfDay < 12 && timeOfDay > 5) {
    System.out.println("Good morning, California!");
}
```

The `||` operator, known as "or", tests whether at least one of two booleans is true. 

```java
if (timeOfDay <= 5 || timeOfDay >= 12) {
    System.out.println("Hello world!");
}
```

You can string as many of these together as you like, adding parentheses as needed: 

```java
if (timeOfDay == 4 || timeOfDay < 4 && (timeOfDay > 2 || (timeOfDay < 2 && timeOfDay > 1) || timeOfDay == 2) || timeOfDay == 1) {
    System.out.println("You go sleep; I don't need sleep");
}
```
