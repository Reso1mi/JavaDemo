package base_thread_study.chapter7;

public class SynchronizedClass {
    public static void main(String[] args) {
        Thread thread0 = new Thread(() -> ClassLock.m1(), "Thread0");
        thread0.start();
        Thread thread1 = new Thread(() -> ClassLock.m2(), "Thread1");
        thread1.start();
        Thread thread2 = new Thread(() -> ClassLock.m3(), "Thread2");
        thread2.start();
    }
}

class ClassLock {

    static {
        synchronized (ClassLock.class) {
            System.out.println("static" + Thread.currentThread().getName());
            try {
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized void m1() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void m2() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void m3() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}