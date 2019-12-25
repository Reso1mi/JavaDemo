package base_thread_study.chapter6;

import java.util.concurrent.TimeUnit;

/**
 * Created by hongjie on 2017/7/7.
 */
public class NotifyInter {

    volatile int a = 0;

    public static void main(String[] args) {

        Object object = new Object();

        NotifyInter waitNotify = new NotifyInter();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (object) {
                    System.out.println("线程1 获取到监视器锁");
                    try {
                        object.wait();
                        System.out.println("线程1 正常恢复啦。但是 isInterrupt = "+ Thread.currentThread().isInterrupted());
                    } catch (InterruptedException e) {
                        System.out.println("线程1 wait方法抛出了InterruptedException异常");
                    }

                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println("在sleep中被中断");
                    }
                }
            }
        }, "线程1");
        thread1.start();
        // 这里让 thread1 和 thread2 先起来，然后再起后面的 thread3
       try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object) {
                    System.out.println("线程3 拿到了监视器锁。");
                    System.out.println("线程3 设置线程1中断");
                    object.notify(); //2
                    waitNotify.a = 1; // 这行是为了禁止上下的两行中断和notify代码重排序
                    thread1.interrupt(); // 1
                    System.out.println("线程3 调用notify");
                    System.out.println("线程3 调用完notify后，休息一会");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    System.out.println("线程3 休息够了，结束同步代码块");
                }
            }
        }, "线程3").start();
    }
}
