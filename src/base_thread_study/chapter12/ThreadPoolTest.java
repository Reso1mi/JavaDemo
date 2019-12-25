package base_thread_study.chapter12;

import java.util.stream.IntStream;

public class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        SimpleThreadPool threadPool= new SimpleThreadPool();
        IntStream.rangeClosed(0, 40)
                .forEach(i -> {
                    threadPool.submit(() -> {
                        System.out.println("The task " + i + "  runnable by thread " + Thread.currentThread().getName() + " start");
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("The task " + i + "  runnable by thread " + Thread.currentThread().getName() + " end");
                    });
                    System.out.println("submit " + i);
                });
        threadPool.shutdown();
    }
}
