
public class demoSafe extends Thread {
    public static void main(String[] args) {
        HealthAdapter.setHealth(100);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                HealthAdapter.decrement();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}