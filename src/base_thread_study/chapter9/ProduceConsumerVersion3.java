package base_thread_study.chapter9;

import java.util.stream.Stream;
public class ProduceConsumerVersion3 {
    public static void main(String[] args) {
        ProduceConsumerVersion3 pc = new ProduceConsumerVersion3();
        Stream.of("Produce1", "Produce2", "Produce3", "Produce4").forEach(n -> {
            new Thread(() -> {
                while (true) {
                    pc.produce();
                }
            }, n).start();
        });
        Stream.of("Consumer1", "Consumer2", "Consumer3").forEach(n -> {
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
                    LOCK.wait(); //加入LOCK锁的wait队列
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "-->" + (++i));//produce
            isProduced = true;
            LOCK.notifyAll();
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
            LOCK.notifyAll();
        }
    }
}
