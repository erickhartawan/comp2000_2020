import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class HealthAdapter {
    static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static int getHealth() {
        lock.readLock().lock();
        try {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Health.get();
        } finally {
            lock.readLock().unlock();
        }
    }

    public static void setHealth(int x) {
        lock.writeLock().lock();
        try {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Health.set(x);
        } finally {
            lock.writeLock().unlock();
            System.out.println("Health set and writeLock unlocked");
        }
    }

    public static void decrement() {
        lock.writeLock().lock();
        try {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Health.decrement();
            System.out.println(Health.get());
        } finally {
            lock.writeLock().unlock();
            System.out.println("Health decremented and writeLock unlocked");
        }
    }

    public static boolean isDepleted() {
        lock.readLock().lock();
        try {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Health.depleted();
        } finally {
            lock.readLock().unlock();
            System.out.println("Health checked if depleted and readLock unlocked");
        }
    }
}