package juc_study.atomic;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * @author imlgw.top
 * @date 2019/4/30 16:10
 */
public class UnsafeTest {
    public static void main(String[] args) throws InterruptedException, NoSuchFieldException {
        /*Unsafe unsafe=Unsafe.getUnsafe();
        System.out.println(unsafe);*/
        //反射获取unsafe

        //覆盖classload获取unsafe
        long start = System.currentTimeMillis();
        ExecutorService service = Executors.newFixedThreadPool(1000);
        /**
         * wa
         * counter :66182804
         * time :172
         *
         * synchronized
         * counter :100000000
         * time :4520
         *
         * lock
         * counter :100000000
         * time :2830
         *
         * atomic
         * counter :100000000
         * time :3391
         *
         * consume
         * counter :100000000
         * time :8678
         */
        //Counter counter = new SillyCounter();
        //Counter counter = new LockCounter();
        //Counter counter = new AtomicCounter();
        Counter counter = new CASCounter();
        for (int i = 0; i < 1000; i++) {
            service.submit(new CounterRunnable(counter, 100000));
        }
        /*for (int i = 0; i < 1000; i++) {
            service.submit(()->{
                for (int j = 0; j < 300000; j++) {
                    counter .increment();
                }
            });
        }*/
        service.shutdown();
        service.awaitTermination(1, TimeUnit.HOURS);
        System.out.println("counter :" + counter.getCounter());
        System.out.println("time :" + (System.currentTimeMillis() - start));

        Unsafe unsafe = getUnsafe();
    }

    interface Counter {
        void increment();

        long getCounter();
    }

    static class CounterRunnable implements Runnable {
        private final Counter counter;

        private final long num;

        public CounterRunnable(Counter counter, long num) {
            this.counter = counter;
            this.num = num;
        }

        @Override
        public void run() {
            for (long i = 0; i < num; i++) {
                counter.increment();
            }
        }
    }


    static class SillyCounter implements Counter {
        private long counter = 0;

        @Override
        public void increment() {
            counter++;
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    static class AtomicCounter implements Counter {
        private AtomicLong counter = new AtomicLong(0);

        @Override
        public void increment() {
            counter.incrementAndGet();
        }

        @Override
        public long getCounter() {
            return counter.get();
        }
    }

    //获取Unsafe
    public static Unsafe getUnsafe() {
        Field f = null;
        try {
            f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe)f.get(null);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    static class CASCounter implements Counter {
        private volatile long counter = 0;

        public CASCounter() throws NoSuchFieldException {
            unsafe = getUnsafe();
            //字段的内存偏移量
            offset= unsafe.objectFieldOffset(CASCounter.class.getDeclaredField("counter"));
            System.out.println(offset);
        }

        private Unsafe unsafe;
        private long offset;


        @Override
        public void increment() {
            long current=counter;
            while (!unsafe.compareAndSwapLong(this,offset,current,current+1)){
                current=counter;
            }
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    static class LockCounter implements Counter {
        private long counter = 0;
        Lock lock = new ReentrantLock();

        @Override
        public void increment() {
            try {
                lock.lock();
                counter++;
            } finally {
                lock.unlock();
            }
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }
}
