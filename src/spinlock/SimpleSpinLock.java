package spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author imlgw.top
 * @date 2019/8/10 20:59
 */
public class SimpleSpinLock {
    private AtomicReference<Thread> owner = new AtomicReference<Thread>();

    public void lock() {
        Thread currentThread = Thread.currentThread();

        // 如果锁未被占用，则设置当前线程为锁的拥有者
        // 后面解锁的就只能是当前线程
        while (!owner.compareAndSet(null, currentThread)) {
        }
    }

    public void unlock() {
        Thread currentThread = Thread.currentThread();

        // 只有锁的拥有者才能释放锁
        owner.compareAndSet(currentThread, null);
    }
}

