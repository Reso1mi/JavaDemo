package base_thread_study.chapter9;

import java.util.stream.Stream;

/**
 * single  p&c
 */
public class ProduceConsumerVersion2 {
    public static void main(String[] args) {
        ProduceConsumerVersion2 pc = new ProduceConsumerVersion2();
        Stream.of("Produce1").forEach(n -> {
            new Thread(() -> {
                while (true) {
                    pc.produce();
                }
            }, n).start();
        });
        Stream.of("Consumer1").forEach(n -> {
            new Thread(() -> {
                while (true) {
                    pc.consumer();
                }
            }, n).start();
        });
    }

    private int i = 0;

    private final Object LOCK = new Object();

    private volatile boolean isProduced = false;

    public void produce() {
        synchronized (LOCK) {
            if (isProduced) {
                //已经生产了
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "-->" + (++i));//produce
            isProduced = true;
            LOCK.notify();
        }
    }

    public void consumer() {
        synchronized (LOCK) {
            if (!isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "-->" + (i));//consumer
            isProduced = false;
            LOCK.notify();
        }
    }
}
