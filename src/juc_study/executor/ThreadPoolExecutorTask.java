package juc_study.executor;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author imlgw.top
 * @date 2019/7/31 19:19
 */
public class ThreadPoolExecutorTask {
    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 20, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), r -> new Thread(r), new ThreadPoolExecutor.AbortPolicy());
        poolExecutor.execute(() -> {
            while (true) {
                System.out.println("you are a pig");
            }
        });
        final List<Runnable> runnables = poolExecutor.shutdownNow();
        System.out.println("i have try to shutdown the pool");
    }
}
