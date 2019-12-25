package juc_study.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author imlgw.top
 * @date 2019/4/22 17:01
 */
public class CASLock {
    private static final AtomicInteger value = new AtomicInteger();

    private Thread lockedThread;

    public void trylock() throws GetLockException {
        boolean success = value.compareAndSet(0, 1);
        if (!success) {
            throw new GetLockException("获得锁失败");
        }
        lockedThread = Thread.currentThread();
    }

    public void unlock() {
        if (0 == value.get()) {
            return;
        }
        if (lockedThread == Thread.currentThread()) {
            boolean success = value.compareAndSet(1, 0);
            System.out.println(Thread.currentThread().getName() + "dasdsadsa");
        }
    }
}
