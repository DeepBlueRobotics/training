You'd probably want your Hello World program to have messages for more than two times of day. You could try this: 

_HelloWorld.java_
```java
public class HelloWorld {
    public static void main(String[] args) {
        int timeOfDay = 9;
        if (timeOfDay < 5) {
            System.out.println("You go sleep; I don't need sleep");
        }
        if (timeOfDay >= 5 && timeOfDay < 12) {
            System.out.println("Good morning, California!");
        }
        if (timeOfDay >= 12 && timeOfDay < 23) {
            System.out.println("Hello World!");
        }
        else {
            System.out.println("Don't ping Kevin at this time smh");
        }
    }
}
```

But what if you then decide that people should be asleep at 5 in the morning as well? You would have to remember to change that number in two places. And if you forget an equal sign, you skip an hour of the day. 

The `else if` statement makes this kind of task easier. 

```java
if (timeOfDay < 5) {
    System.out.println("You go sleep; I don't need sleep");
} else if (timeOfDay < 12) {
    System.out.println("Good morning, California!");
} else if (timeOfDay < 23) {
    System.out.println("Hello World!");
} else {
    System.out.println("Don't ping Kevin at this time smh");
}
```

The logic in these `else if` lines is that if the first condition evaluates to `false`, the computer checks if the next `else if` condition is true, and if that is `false` too, the computer moves on to the next `else if`. 