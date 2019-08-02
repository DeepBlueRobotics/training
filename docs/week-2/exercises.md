##Letter Grades
This program is supposed to print a letter grade equivalent to a percentage. What does it actually print? 

_LetterGrades.java_
```java
public class LetterGrades {
    public static void main(String[] args) {
        int grade = 95;
        if (grade >= 90) {
            System.out.println("A");
        } if (grade >= 80) {
            System.out.println("B");
        } if (grade >= 70) {
            System.out.println("C");
        } if (grade >= 60) {
            System.out.println("D");
        } else {
            System.out.println("F");
        }
    }
}
```

??? example "Solution" 
    Each `if` statement after the first is checked regardless of what comes before it. The program should have stopped checking after the first `if`. 

    The output should look like this: 
    ```
    A
    B
    C
    D
    ```

##Fours 
How many times does the following program print a 4? 

_Fours.java_
```java
public class Fours {
    public static void main(String[] args) {
        for (int i = 0; i <= 4; i++) {
            System.out.println(4);
        }
    }
}
```

??? example "Solution" 
    The loop actually runs five times: the condition `i <= 4` is true when `i` is `0`, `1`, `2`, `3`, and `4`. 

    When `i` starts at `0` and the condition has a `<` sign instead of `<=`, as it usually does, the number of times the loop is run is whatever number is in the condition. 

##Asterisks
Try writing a program that prints asterisks (`*`) like this: 

```
*
**
***
****
*****
```

Use `System.out.print("string");` to print without going to a new line. Here's an empty program to start with. 

_Asterisks.java_
```java
public class Asterisks {
    public static void main(String[] args) {

    }
}
```

??? example "Possible Answer" 
    _Asterisks.java_
    ```java
    public class Asterisks {
        public static void main(String[] args) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j <= i; j++) {
                    System.out.print("*");
                }
                System.out.println();
            }
        }
    }
    ```
