package juc_study.tools.condition;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

/**
 * @author imlgw.top
 * @date 2019/7/26 15:09
 */
public class ConditionTest1 {

    private static final ReentrantLock LOCK = new ReentrantLock();

    private static final Condition pCondition = LOCK.newCondition();

    private static final Condition cCondition = LOCK.newCondition();

    private static LinkedList<Long> buffer = new LinkedList<>();

    private static final Integer MAX_BUFFER = 5;

    public static void main(String[] args) {
        ConditionTest1 conditionTest1 = new ConditionTest1();
        Stream.of("p1", "p2", "p3", "p4", "p5", "p6", "p7", "p8", "p9", "p10", "p11").forEach(name -> {
            new Thread(() -> conditionTest1.produce(), name).start();
        });

        Stream.of("c1", "c2", "c3", "c4", "c5", "c6", "c7", "c8", "c9", "c10", "c11").forEach(name -> {
            new Thread(() -> conditionTest1.consumer(), name).start();
        });
    }

    public void produce() {
        try {
            LOCK.lock();
            while (buffer.size() >= MAX_BUFFER) {
                pCondition.await();
            }
            long l = System.currentTimeMillis();
            buffer.add(l);
            System.out.println(Thread.currentThread().getName() + " produced " + l + " ,current buffer size " + buffer.size());
            cCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }

    public void consumer() {
        try {
            LOCK.lock();
            while (buffer.size() == 0) {
                cCondition.await();
            }
            Long aLong = buffer.removeLast();
            System.out.println(Thread.currentThread().getName() + " consumer " + aLong + " ,current buffer size " + buffer.size());
            pCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }

}
