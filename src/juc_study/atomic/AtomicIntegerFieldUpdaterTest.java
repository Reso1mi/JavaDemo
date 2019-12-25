package juc_study.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.stream.Stream;

/**
 * @author imlgw.top
 * @date 2019/4/29 16:00
 */
public class AtomicIntegerFieldUpdaterTest {
    public static void main(String[] args) {
        final  AtomicIntegerFieldUpdater updater = AtomicIntegerFieldUpdater.newUpdater(TestUpdate.class,"num");
        TestUpdate testUpdate = new TestUpdate();
        updater.compareAndSet(testUpdate,0,1);
        System.out.println(updater.get(testUpdate));

       /* Stream.of("t1", "t2", "t3", "t4", "t5").forEach(s -> {
            new Thread(() -> {
                int MAX = 100;
                for (int i = 0; i < MAX; i++) {
                    System.out.println(updater.getAndIncrement(testUpdate));
                }
            }, s).start();
        });*/
    }


    static class TestUpdate {
        volatile int num;
    }
}
