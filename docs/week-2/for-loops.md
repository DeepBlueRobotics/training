A more compact (and often preferred) type of loop is the `for` loop, which is meant for running code a predetermined number of times. 

_Quack.java_
```java
public class Quack {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println(i + " quack");
        }
    }
}
```

This prints out the same as before, but condenses the code: 

- `i` is a variable initialized as `0` that serves as our index. By convention, `for` loop indices are named single letters `i` or `j`. 
- The second part, `i < 5`, is the same as the condition in the while loop. 
- `i++` increments the index, and since it's part of `for` loop syntax, you won't forget it. 

Although for loops are convenient, while loops have their uses. For example, when you want to loop something until the user does something, you don't need an index and have to use the while loop. 

##Combining logic elements 

You can combine `if` statements and loops all you want. Just make sure you can understand your code later. 

_WaterGame.java_ 
```java
public class WaterGame {
    public static void main(String[] args) {
        int randomNum = 11;
        for (int i = 2020; i <= 2025; i++) {
            if (randomNum == 17) {
                while (true) {
                    System.out.println("water game " + i);
                }
            } else {
                System.out.println("water game " + (i + 1) + "??");
                randomNum += 2;
            }
        }
    }
}
```
