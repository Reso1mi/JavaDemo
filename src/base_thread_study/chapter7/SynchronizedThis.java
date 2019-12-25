package base_thread_study.chapter7;

public class SynchronizedThis {
    public static void main(String[] args) {
        ThisLock thisLock = new ThisLock();
        Thread thread = new Thread(() -> thisLock.m1(), "Thread0");
        thread.start();
        Thread thread1 = new Thread(() -> thisLock.m2(), "Thread1");
        thread1.start();
        Thread thread2 = new Thread(() -> thisLock.m3(), "Thread2");
        thread2.start();
    }
}

class ThisLock {

    private final Object LOCK = new Object();

    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void m2() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void m3() {
        synchronized (LOCK) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}