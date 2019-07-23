There are four main data types we use in robotics: int, double, boolean, and String.

## Integers
Integer types are pretty intuitive. Integer variables are created with the keyword `int`.
```java
int four = 4;
```
You can perform basic arithmetic operations with integers:
```java
int five = 3 + 2;         // addition
int three = five - 2;     // subtraction
int nine = three * three; // multiplication
int one = 9 / nine;       // division
```
Operations also follow PEMDAS, except there's no exponent operation.
```java
3 + 2 * 5; // 13
(3 + 2) * 5; // 25
3 + (2 * 5); // 13
```

!!! note
    When the result of a division between two ints is a fraction, the output will always round down.
    ```java
    int something = 9 / 2; // will result in 4
    ```

Java provides some shortcuts for operating on numbers. Say you want to add 2 to a number. You can do this in two different ways:
```java
num = num + 2; // long form
num += 2; // short form
```
The same thing also works with subtraction, multiplication, and division.
```java
num -= 2;
num *= 2;
num /= 2;
```

When you want to only increment or decrement by one, Java provides an even easier solution: `++` and `--` respectively. Here's how you use it:
```java
num = num + 1; // long form
num += 1; // short form
num++; // even shorter form

num = num - 1; // long form
num -= 1; // short form
num--; // even shorter form
```

## Doubles
But what if you wanted to use more than just whole numbers? For that purpose, we have the `double` data type. You can use it the same way you use ints.
```java
double six = 6.0;
double threeHalves = six / 4;
double two = threeHalves + 0.5;
// etc
```

## Booleans
Boolean is the most basic type in Java. A boolean variable can either be `true` or `false`.
```java
boolean yes = true;
boolean no = false;
```
Even though they're super simple, we actually use booleans a lot. For example, when comparing the values of two things, a boolean is returned.

Here are a few comparison operators that we use:
```java
2 == 2; // equality
3 != 2; // inequality
2 < 3;  // less than
3 > 2;  // greater than
2 <= 2; // less than or equal
3 >= 3; // greater than or equal
```
These comparison operators can be used to compare ints, doubles, and booleans, but not Strings. 

## Strings
Earlier, you were introduced to the `String` type. All the previous types we covered are called **primitive** types, but `String` is not a primitive type. For now, this distinction only affects two things: the type name is capitalized and you can't compare Strings directly with the comparison operators we mentioned above.


Here are a few more things you can do with Strings.

String concatenation with the `+` sign:
```java
"Hello" + " world"; // "Hello world"
```
You can also do this with Strings and other types:
```java
"Team " + 199; // "Team 199"
```

You can get the length of a string with `.length()`:
```java
String looong = "it took me a while to count the number of characters in this string";
System.out.println(looong.length()); // 67
```