package juc_study.tools.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author imlgw.top
 * @date 2019/7/24 17:10
 */
public class CyclicBarierTest2 {

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(3);

        new Thread(()->{
            try {
                //Thread.sleep(3000);
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                //Thread.sleep(3000);
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

    /*    new Thread(()->{
            try {
                Thread.sleep(3000);
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();*/
        Thread.sleep(1000);
        //cyclicBarrier.reset();
        System.out.println("???????");
    }
}
