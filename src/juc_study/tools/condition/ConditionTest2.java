package juc_study.tools.condition;

import javax.xml.crypto.Data;
import java.sql.Time;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author imlgw.top
 * @date 2019/7/26 17:48
 */
public class ConditionTest2 {
    private static final Object obj = new Object();

    private static final ReentrantLock LOCK = new ReentrantLock();

    private static final Condition condition = LOCK.newCondition();

    private static boolean isReady = false;

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            //3s+
            Date date = new Date(System.currentTimeMillis() + 3000);
            try {
                LOCK.lock();
                while (!isReady) {
                    boolean b = condition.awaitUntil(date);
                    if (!b) {
                        System.out.println("Fucking t0!!!!!, i am timeout");
                        return;
                    }
                    System.out.println("oh i get the lock!!!");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                LOCK.unlock();
            }
        }, "t1").start();

        TimeUnit.SECONDS.sleep(5);

        new Thread(() -> {
            try {
                LOCK.lock();
                isReady = true;
                condition.signal();
            } finally {
                LOCK.unlock();
            }
        }, "t0").start();

    }
}
