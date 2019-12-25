package base_thread_study.chapter5;

import java.util.stream.IntStream;

public class ThreadJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread t0 = new Thread(() -> {
            IntStream.range(1, 1000)
                    .forEach(i -> System.out.println(Thread.currentThread().getName() + "-->" + i));
        });
        t0.start();
        Thread t1 = new Thread(() -> {
            IntStream.range(1, 1000)
                    .forEach(i -> System.out.println(Thread.currentThread().getName() + "-->" + i));
        });
        t1.start();
        t0.join();
        t1.join();
        //join 是当前线程会等待子线程执行结束才会执行
        //所以这里主线程会等待t0,t1
        IntStream.range(1, 1000)
                .forEach(i -> System.out.println(Thread.currentThread().getName() + "-->" + i));
    }
}
