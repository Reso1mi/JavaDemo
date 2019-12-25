package juc_study.tools.StampedLock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * @author imlgw.top
 * @date 2019/7/28 13:47
 */
public class StampedLockTest1 {

    private static final StampedLock stampedLock = new StampedLock();

    private volatile static List<Long> shareData = new ArrayList<>();

    public static void main(String[] args) {
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        Runnable readRunnable = () -> {
            while (true) {
                read();
            }
        };

        Runnable writeRunnable = () -> {
            while (true) {
                write();
            }
        };
        executorService.submit(readRunnable);
        executorService.submit(readRunnable);
        executorService.submit(readRunnable);
        executorService.submit(readRunnable);
        executorService.submit(readRunnable);
        executorService.submit(readRunnable);
        executorService.submit(readRunnable);
        executorService.submit(readRunnable);
        executorService.submit(readRunnable);

        executorService.submit(writeRunnable);

    }

    private static void read() {
        long stamp = stampedLock.tryOptimisticRead();
        //暂存res
        String res=Thread.currentThread().getName() + " read " + shareData;
        if (!stampedLock.validate(stamp)) {
            try {
                //验证失败,说明有线程进行了写操作,可能造成数据不一致
                //进行锁升级,获取共享读锁
                stamp = stampedLock.readLock();
                //覆盖res
                res=Thread.currentThread().getName() + " read " + shareData;
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }
        System.out.println(res);
    }


    private static void write() {
        long stamp = -1;
        try {
            stamp = stampedLock.writeLock();
            shareData.add(System.currentTimeMillis());
            System.out.println(Thread.currentThread().getName() + " Write ");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }
}
