package juc_study.tools.CountDownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author imlgw.top
 * @date 2019/7/24 15:09
 */
public class CountDownLatchTest2 {
    private static int data;

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch=new CountDownLatch(4);

        new Thread(() -> {
            for (int i = 1; i <10; i++) {
                data=i;
                latch.countDown();
                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        latch.await();
        System.out.println("data:"+data);
    }
}
