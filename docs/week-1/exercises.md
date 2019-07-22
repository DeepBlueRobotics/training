## Ints and Doubles
Without actually running it, what does this program output when run?

_IntDouble.java_
```java
class IntDouble {
  public static void main(String[] args) {
    int a = 8;
    int b = 3;
    double c = 0.25;
    System.out.println((a / b) * c);
  }
}
```

??? example "Solution"
    The first thing that is evaluated is `a / b`. Since they are both integers, the result is 8/3 rounded down to 2.

    Then that number is multiplied by `c`. Since `c` is a double, this expression evaluates exactly. Thus 2 * 0.25 = `0.5`

## String Cheese
Without actually running it, what does this program output when run?

_StringCheese.java_
```java
class StringCheese {
  public static void main (String[] args) {
    String a = "string";
    String b = "cheese";
    int c = 5;
    double d = 3.0;

    System.out.println(a.length() + d + a + "b" + (b.length() / c));
  }
}
```

??? example "Solution"
    First, evaluate the parentheses. `b.length() / c` is the same as `6 / 5`, and they're both ints, so the value in the parentheses is 1.

    Then, go through it left to right.

    1. `a.length() + d = 6 + 3.0 = 9.0`
    2. `9.0 + a = 9.0 + "string" = "9.0string"`
    3. `"9.0string" + "b" = "9.0stringb"`
    4. `"9.0stringb" + (b.length() / c) = "9.0stringb" + 1 = "9.0stringb1"`

    So `9.0stringb1` is the final answer.

## 24 Game
The 24 Game is a game where you are given 4 cards and your objective is to find a way to manipulate the numbers corresponding to the cards to form the number 24. For example, if your cards were 4, 7, 8, and 8, you could do `(7 - (8 / 8)) * 4 = 24`. Given four variables, print out `24` or `24.0` to the console using only the values from four variables.

_TwentyFour.java_
```java
class TwentyFour {
  public static void main(String[] args) {
    String a = "nine";
    int b = 5;
    int c = 2;
    double d = 3.0;

    // your code here
  }
}
```

??? example "Possible Answer"
    _TwentyFour.java_
    ```java
    class TwentyFour {
      public static void main(String[] args) {
        String a = "nine";
        int b = 5;
        int c = 2;
        double d = 3.0;

        System.out.println(b / c * a.length() * d);
      }
    }
    ```