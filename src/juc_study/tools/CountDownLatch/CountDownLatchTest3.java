package juc_study.tools.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author imlgw.top
 * @date 2019/7/24 15:19
 */
public class CountDownLatchTest3 {
    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch=new CountDownLatch(1);

        new Thread(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //latch.countDown();
            }
        }).start();
        latch.await();
        System.out.println("????");
    }
}
