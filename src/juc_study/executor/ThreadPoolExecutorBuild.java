package juc_study.executor;

import java.util.concurrent.*;

/**
 * @author imlgw.top
 * @date 2019/7/30 19:33
 */
public class ThreadPoolExecutorBuild {

    /*
     * @param corePoolSize the number of threads to keep in the pool, even
     *        if they are idle, unless {@code allowCoreThreadTimeOut} is set
     * @param maximumPoolSize the maximum number of threads to allow in the
     *        pool
     * @param keepAliveTime when the number of threads is greater than
     *        the core, this is the maximum time that excess idle threads
     *        will wait for new tasks before terminating.
     * @param unit the time unit for the {@code keepAliveTime} argument
     * @param workQueue the queue to use for holding tasks before they are
     *        executed.  This queue will hold only the {@code Runnable}
     *        tasks submitted by the {@code execute} method.
     * @param threadFactory the factory to use when the executor
     *        creates a new thread
     * @param handler the handler to use when execution is blocked
     *        because the thread bounds and queue capacities are reached
     */
    public static void main(String[] args) {
        //1.coreSize=1 maxSize=2 queue=empty submit=3
        //2.coreSize=1 maxSize=2 queue=empty submit=3
        //3.coreSize=1 maxSize=2 queue=empty submit=3
        //4.coreSize=1 maxSize=2 queue=empty submit=3
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) buildThreadPoolExecutor();
        int activeCount=-1;
        int queueSize=-1;
        while (true) {
            if (queueSize!=executorService.getQueue().size()||activeCount!=executorService.getActiveCount()) {
                System.out.println("active: "+executorService.getActiveCount());
                System.out.println("core: "+executorService.getCorePoolSize());
                System.out.println("queue: "+executorService.getQueue().size());
                System.out.println("max: "+executorService.getMaximumPoolSize());
                queueSize=executorService.getQueue().size();
                activeCount=executorService.getActiveCount();
                System.out.println("==================================");
            }
        }
    }

    private static ExecutorService buildThreadPoolExecutor() {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 2, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), r -> {
            Thread thread = new Thread(r);
            return thread;
        }, new ThreadPoolExecutor.AbortPolicy());
        System.out.println("Thread pool is create done.");
        poolExecutor.execute(()->doSomething(100));
        poolExecutor.execute(()->doSomething(100));
        poolExecutor.execute(()->doSomething(100));
        return poolExecutor;
    }

    private static void doSomething(int seconds){
        try {
            System.out.println(Thread.currentThread().getName()+" is running");
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
