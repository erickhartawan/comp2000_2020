/*
    This class is created to demonstrate the bug / non thread safe condition inside HEalth class. we created a new Health class with 100 value and perform decrement
    by creating 100 threads which decrement health by 1 and print out the remaining. instead of 100 lines as expected, we only receive 88 output which indicates
    there is a bug in the implementation
*/
//not threadsafe
public class demo extends Thread {
    Actor camel1 = new Camel(null, 0);
    static Health testHealth = new Health();

    public static void main(String[] args) {
        setHealth(100);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                Health.decrement();
                System.out.println(Health.get());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            ).start();
        }
    }

    public static void setHealth(int num) {
        Health.set(num);
    }
}

/*
 * SAMPLE OUTPUT 99 96 93 94 90 95 97 88 98 89 91 83 82 81 92 79 80 86 84 85 87
 * 68 69 70 71 72 73 74 75 76 77 78 4 4 10 6 7 9 8 1 0 2 13 3 11 12 17 14 38 15
 * 16 18 19 20 31 32 33 34 35 36 39 40 41 43 42 44 45 47 48 49 50 51 52 53 54 56
 * 55 57 58 59 60 61 62 63 64 65 66 67
 */
