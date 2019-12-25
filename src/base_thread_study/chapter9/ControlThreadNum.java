package base_thread_study.chapter9;

import java.util.*;

public class ControlThreadNum {

    private static final LinkedList THREADS = new LinkedList<>();

    private final static int MAX_THREAD = 5;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<Thread> worker = new ArrayList();
        //创建了十个线程，但是控制每次最多同时运行的只有5个
        Arrays.asList("M1", "M2", "M3", "M4", "M5", "M6", "M7", "M8", "M9", "M10").stream().map(ControlThreadNum::captureThread).forEach(t -> {
            t.start();
            worker.add(t);
        });
        //等待worker的线程都执行完
        worker.stream().forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Optional.of("All capture is done").ifPresent(System.out::println);
        Optional.of(System.currentTimeMillis() - start).ifPresent(System.out::println);
    }

    private static Thread captureThread(String name) {
        return new Thread(() -> {
            Optional.of("Thread " + Thread.currentThread().getName() + "  is begin").ifPresent(System.out::println);
            synchronized (THREADS) {
                while (THREADS.size() >= MAX_THREAD) {
                    //同一时间执行的线程数量大于最大线程数量
                    try {
                        THREADS.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //THREADS 只是用来控制数量&锁 元素是什么并不重要
                THREADS.addLast(1);
            }
            //到这里是并行
            Optional.of("Thread " + Thread.currentThread().getName() + "  is running").ifPresent(System.out::println);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (THREADS) {
                Optional.of("Thread " + Thread.currentThread().getName() + "  is end").ifPresent(System.out::println);
                THREADS.removeLast();
                THREADS.notifyAll();
            }
        }, name);
    }
}

