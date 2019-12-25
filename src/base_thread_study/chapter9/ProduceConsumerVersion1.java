package base_thread_study.chapter9;

public class ProduceConsumerVersion1 {
    public static void main(String[] args) {
        ProduceConsumerVersion1 pc = new ProduceConsumerVersion1();
        new Thread(() -> {
            while (true) {
                pc.produce();
            }
        }, "Produce").start();
        new Thread(() -> {
            while (true) {
                pc.consumer();
            }
        }, "Consumer").start();
    }

    private int i = 0;

    private final Object LOCK = new Object();

    public void produce() {
        synchronized (LOCK) {
            System.out.println("Produce->" + (i++));
        }
    }

    public void consumer() {
        synchronized (LOCK) {
            System.out.println("Consumer->" + (i));
        }
    }
}
