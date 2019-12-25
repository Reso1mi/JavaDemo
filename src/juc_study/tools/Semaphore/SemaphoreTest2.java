package juc_study.tools.Semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author imlgw.top
 * @date 2019/7/25 16:36
 */
public class SemaphoreTest2 {

    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore=new Semaphore(5);

        new Thread(()->{
            try {
                //获取所有的凭证
                semaphore.drainPermits();
                System.out.println("t1 获取所有凭证");
                System.out.println("可用："+semaphore.availablePermits());
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
        }).start();

        TimeUnit.SECONDS.sleep(4);

        new Thread(()->{
            try {
                boolean b = semaphore.tryAcquire();
                System.out.println("当前"+semaphore.availablePermits());
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
