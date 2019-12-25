package juc_study.tools.Lock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author imlgw.top
 * @date 2019/8/3 13:00
 */
public class ReentrantLockTest2 {

    private final static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        te:
        for (int i = 0; i >= -1; i++) {
            if (i == 10) {
                continue te;
            }
            TimeUnit.SECONDS.sleep(1);
            System.out.println(i);
        }




        //new Thread(()->lock2()).start();

        //new Thread(()->lock1()).start();
    }

    private static void lock2() {
        final ReentrantLock lock2 = lock;
        try {
            lock2.lock();
            System.out.println("i am lock11");
            TimeUnit.SECONDS.sleep(4);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock2.unlock();
        }
    }

    private static void lock1() {
        final ReentrantLock lock1 = lock;
        try {
            lock1.lock();
            System.out.println("i am lock22222222");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock1.unlock();
        }
    }


}
