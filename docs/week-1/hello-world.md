Let's start with the classic Hello World program. Open VSCode, create a new file, paste the code below into the file, and save the file as `HelloWorld.java`.

_HelloWorld.java_
```java
public class HelloWorld {
  public static void main(String[] args) {
    // prints hello world
    System.out.println("Hello world!");
  }
}
```

Now, still in VSCode, right click on the file and select "Run Code". A console will pop out from the bottom. You should see something like this:
```bash
[Running] cd "/home/kev/dev/training/" && javac HelloWorld.java && java HelloWorld
Hello world!

[Done] exited with code=0 in 0.516 seconds
```

You just ran your first Java program!

There's a lot going on in this program, but don't worry too much about it for now. Just take note of a few things:

- The stuff between the curly braces after `public static void main(String[] args)` is what's run.
- `System.out.println();` prints things to the console.
- The line `System.out.println("Hello world!");` is a statement. In Java, we use semicolons to denote the end of statements.
- Comments in Java code start with two forward slashes `// like this` and are not part of code execution.