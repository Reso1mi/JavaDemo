package juc_study.tools.Lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author imlgw.top
 * @date 2019/7/26 12:15
 */
public class ReadWriteLockTest1 {

    private final static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(false);

    private final static Lock readLock = readWriteLock.readLock();

    private final static Lock writeLock = readWriteLock.writeLock();

    private static final List<Long> data = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        Thread thread0 = new Thread(() -> write());
        thread0.start();

        Thread thread1 = new Thread(() -> read());
        thread1.start();

        Thread thread2 = new Thread(() -> read());
        thread2.start();
    }

    public static void write() {
        try {
            writeLock.lock();
            data.add(System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(5);
            System.out.println("write");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public static void read() {
        try {
            readLock.lock();
            TimeUnit.SECONDS.sleep(5);
            data.forEach(System.out::println);
            System.out.println(Thread.currentThread().getName() + " ====");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

}
