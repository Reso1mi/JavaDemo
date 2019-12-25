package juc_study.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author imlgw.top
 * @date 2019/8/1 18:54
 */
public class ExecutorsExample1 {
    public static void main(String[] args) throws InterruptedException {
        //useCacheThreadPool();
        useSingleThread();
    }

    private static void useCacheThreadPool() throws InterruptedException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        System.out.println(executorService.getActiveCount());
        executorService.execute(()->{
            System.out.println("========");
        });
        System.out.println(executorService.getActiveCount());

        IntStream.range(0,100).boxed().forEach(i->executorService.execute(()->{
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+": "+i);
        }));
        TimeUnit.SECONDS.sleep(1);
        System.out.println(executorService.getActiveCount());
    }

    private static void useFixThread() throws InterruptedException {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    }

    private static void useSingleThread() throws InterruptedException {
        //err
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newSingleThreadExecutor();
        executorService.getActiveCount();
    }

}
