package juc_study.tools.Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author imlgw.top
 * @date 2019/7/25 20:07
 */
public class ReentrantLockTest1 {

    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread thread0 = new Thread(() -> needLock() );
        Thread thread1 = new Thread(() -> needLock() );
        thread0.start();
        thread1.start();
        TimeUnit.SECONDS.sleep(3);
        thread1.interrupt();
    }

    public static void needLock() {
        try {
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName()+" is get the lock");
            while (true){

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
