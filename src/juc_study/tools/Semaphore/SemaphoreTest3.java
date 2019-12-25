package juc_study.tools.Semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author imlgw.top
 * @date 2019/7/25 17:01
 */
public class SemaphoreTest3 {
    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore=new Semaphore(1);

        new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println("t2获取到锁");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
        }).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
            try {
                boolean b = semaphore.tryAcquire();
                if (!b) {
                    System.out.println("t2获取 失败");
                }
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
        }).start();

    }
}
