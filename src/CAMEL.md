## Fixing Camel bug

Our program import Camel from a library which is not editable. So one way of getting around this bug is to delegate the calling of the bugged method into another thread in order to prevent our main thread being put to sleep. We have successfully located that the bug is in setPoly method therefore, in our main Actor implementation, we can modify method that invoke setPoly from each subclasses.
How to implement:

1. We have to extends Actor class with Thread in order to introduce multi-threading to our application

```
public abstract class Actor extends Thread{}
```

2. setLocation method is the method that invoke setPoly, so inside setLocation, we modify the invocation of setPoly from :
   `setPoly();`
   to:

```
    new Thread(() -> {
        setPoly();
        }).start();
```

3. That is it. What it will do now is, whenever we setLocation is called, it will set the location of the cell and when it reaches setPoly, instead of executing it on the main thread, it will be executed by a new thread. After running the code for setPoly, the thread will die.

4. Inside setPoly for camel, snooze must be located at the end of the block because the drawing of the camel sprite is not delayed when its executed by another thread. So I assume when the thread is created, it runs regular setPoly() codes and then put into sleep (by snooze method) before dying.

5. we could be more efficient by not creating new thread for every single actor by implementing a check on the class and only create a new thread for Camel classes not others. but with our current actor implementation, I have problem with creating a check if this class is camel class or not.
