package juc_study;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author imlgw.top
 * @date 2019/8/9 19:14
 */
public class LockSupportTest {
    public static void main(String[] args) {
        Thread main=Thread.currentThread();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
            }
            main.interrupt();
        }).start();
        System.out.println("wait unpark");
        LockSupport.park("ddd");
        System.out.println("????????????");
        System.out.println(Thread.interrupted());
        System.out.println(Thread.interrupted());
    }
}
