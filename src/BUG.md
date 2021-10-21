## Explanation to camel bug

It looked like the bug is coming from Camel class. Everytime location of camel updated, there is a random probability that camel will call the snooze method, which contains a function call that is similar to wait(x) or Thread.sleep(x) where x being a random number in millisecond. Suspected is the calling of snooze method happens inside setPoly method and located near the end of the method. Since the program is a single-threaded application, when we hit this function, the main thread is put to sleep for x millisecond, resulting the game freezes.
below is the possible pseudo implementation of camel class

```
Public class Camel extends Actor {
    public camel (); // constructor

    public void snooze(){
        x = random(100,2000) // arbitrary number ( randomly generating 0.1s to 2s)
        wait(x);
    }
    public setPoly(){
        // regular implementation of setPoly to draw a camel and set the location
        // some code to call snooze randomly (sometimes is called sometimes not)
        snooze();
    }
}

```
