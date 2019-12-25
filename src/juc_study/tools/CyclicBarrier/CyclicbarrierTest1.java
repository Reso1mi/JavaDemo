package juc_study.tools.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author imlgw.top
 * @date 2019/7/24 15:54
 */
public class CyclicbarrierTest1 {
    public static void main(String[] args){
        CyclicBarrier cyclicBarrier=new CyclicBarrier(2,()->{
            System.out.println("t1,t2 both arrived");
        });
        new Thread(()->{
            try {
                Thread.sleep(2000);
                System.out.println("t1 finished");
                cyclicBarrier.await();
                System.out.println("other thread is done too");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                Thread.sleep(6000);
                System.out.println("t2 finished");
                cyclicBarrier.await();
                System.out.println("other thread is done too");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
