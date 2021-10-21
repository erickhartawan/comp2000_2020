## Health

Health is not thread safe. This is demonstrated by demo.java. We set health to 100 but after the running 100 threads, we get 0 in 88 executions (my test result. it will be different across different system. Sample output has been provided at the end of demo.java). which is not we are expecting and it is a bug.

## Early stages solution

in the beginning stages of doing this assignments, I came up with a different method to fix the issue. but after reading the specs better and judging the consistency of the behaviour, I have decided to modify the implementation with a separate adapter class. I still include my old implementation to this MD file for reference.

My first solution was to create a new thread for every Health operations. codes below

```java
new Thread(() -> {
    lock.writeLock().lock();
    try {
        try {
        Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Health.set(10);
    } finally {
        lock.writeLock().unlock();
        System.out.println("Health set and writeLock unlocked");
    }
    }).start();
```

\*Implementation to fix Health.set(). By creating a new thread which will acquire lock and release once the operation has finished. if the new thread is put on a waiting state, the main thread will still continue is operation

```java
new Thread(() -> {
            lock.readLock().lock();
            try {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                currentHealth = Health.get();
            } finally {
                lock.readLock().unlock();
                // System.out.println("Health read and readLock unlocked");
            }
        }).start();
```

\*Implementation to fix get health. this include using a temporary variable due to inability of Thread to return value.

# Final Solution

## Health Adapter

A separate abstract class that bridges between static Health class and the rest of the program. Abstract means all other components can access its methods by using `HealthAdapter.<name of method>`

The program currently utilizing 4 methods from Health class.

-   get()
-   set()
-   decrement()
-   depleted()

We implement ReentrantReadWriteLock because the program performs more read than write. Health is constantly checked by the draw() method, by the depleted method at each turn of the game. while writing only occurs in the beginning when we set the health value and each time we perform "Fire" on our enemy.

in the HealthAdapter, we wrap each method with static method that interacts with a ReentrantReadWriteLock. In the beginning of each method, they will try to acquire an approriate lock before they can perform their operation. This will ensure all methods in HealthAdapter is Threadsafe. For example, if a thread is inside setHealth doing its job, another thread won't be able to go inside decrement and perform the operation. If there is no lock implemented, the outcome will be unpredictable therefore considered as a bug. After each operation, thread will unlock the thread to let others take turn.

## demoSafe

demoSafe.java is provided to demonstrate that the implementation of HealthAdapter will make accessing Health class to be thread-safe.
