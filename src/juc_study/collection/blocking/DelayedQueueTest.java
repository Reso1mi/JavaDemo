package juc_study.collection.blocking;

import java.util.concurrent.DelayQueue;

/**
 * @author imlgw.top
 * @date 2019/8/7 17:55
 */
public class DelayedQueueTest {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayedEle> delayQueue = new DelayQueue<DelayedEle>();

        DelayedEle element1 = new DelayedEle(3000,"lgw");
        DelayedEle element2 = new DelayedEle(3000,"top");

        delayQueue.offer(element1);
        delayQueue.offer(element2);
        long l = System.currentTimeMillis();

        System.out.println(l);
        element1 =  delayQueue.take();
        System.out.println(System.currentTimeMillis()-l);

        System.out.println(element1);
    }
}
