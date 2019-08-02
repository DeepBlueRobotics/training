Sometimes we need to program things that involve a lot of repetition, and **loops** help us write simple code for that. One simple type of loop is the `while` loop. 

_Quack.java_
```java
public class Quack {
    public static void main(String[] args) {
        int index = 0;
        while (index < 5) {
            System.out.println(index + " quack");
            index = index + 1;
        }
    }
}
```

The computer checks whether the condition at the top is true, executes the code inside the loop, checks the condition again, executes again, and repeats until the condition is false. Each time the computer goes through the loop, `index` is **incremented** to bring the program closer to ending. 

The above should output this: 

```
0 quack
1 quack
2 quack
3 quack
4 quack
```

Incrementing the index is a crucial step that is very easy to forget. Notice what happens when you delete line 6 in the above code and run it: it keeps printing `0 quack` until you terminate the program. 

##Shortcuts for incrementing 

There are a number of shortened ways to increment an integer in Java: 

```java
index += 4; // index = index + 4;
index -= 4; // index = index - 4;
index *= 2; // index = index * 2;
index /= 2; // index = index / 2;
index++; // index = index + 1;
index--; // index = index - 1;
```

The choice of which syntax to use is up to personal (and organizational) choice. 

##Do-while loops 

These are very similar to `while` loops, except the condition comes at the end. The code inside the loop runs, and _then_ the condition is checked. 

_Quack.java_
```java
public class Quack {
    public static void main(String[] args) {
        int index = 5;
        do {
            System.out.println(index + " quack");
            index = index + 1;
        } while (index < 5);
    }
}
```

Even though the condition evaluates to `false` from the start, the code executes once because the condition isn't checked the beginning. 