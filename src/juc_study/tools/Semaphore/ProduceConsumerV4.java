package juc_study.tools.Semaphore;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.stream.Stream;

/**
 * @author imlgw.top
 * @date 2019/7/25 16:43
 */
public class ProduceConsumerV4 {
    public static void main(String[] args) {
        ProduceConsumerV4 pc = new ProduceConsumerV4();
        Stream.of("Produce1", "Produce2", "Produce3", "Produce4").forEach(n -> {
            new Thread(() -> {
                while (true) {
                    pc.produce();
                }
            }, n).start();
        });
        Stream.of("Consumer1", "Consumer2", "Consumer3", "Consumer4").forEach(n -> {
            new Thread(() -> {
                while (true) {
                    pc.consumer();
                }
            }, n).start();
        });
    }

    private LinkedList<Object> buffer=new LinkedList<>();

    private final Semaphore full = new Semaphore(0);

    private final Semaphore empty = new Semaphore(3);

    //互斥锁
    private final Semaphore mutex= new Semaphore(1);

    public void produce() {
        //已经生产了
        try {
            empty.acquire();
            //不能交换，只有一个信号量
            mutex.acquire();
            buffer.add(new Object());
            System.out.println(Thread.currentThread().getName() + " produce a obj , current list size:" +buffer.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mutex.release();
            full.release();
        }
    }

    public void consumer() {
        try {
            full.acquire();
            mutex.acquire();
            //移除
            buffer.removeLast();
            System.out.println(Thread.currentThread().getName() + " consumer a obj, current size: " + buffer.size());//consumer
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mutex.release();
            empty.release();
        }
    }
}
