package juc_study.executor;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;


class Worker extends AbstractQueuedSynchronizer implements Runnable {

    Worker() {
        //setState(-1); // 设置状态为 -1
    }

    /**
     * Delegates main run loop to outer runWorker
     */
    public void run() {
        System.out.println(Thread.currentThread().getName()+"is running");
    }

    // Lock methods
    //
    // The value 0 represents the unlocked state.
    // The value 1 represents the locked state.
    // 是否获取到了锁
    protected boolean isHeldExclusively() {
        return getState() != 0;
    }

    protected boolean tryAcquire(int unused) {
        //利用CAS设置state
        //很明显这里是个不可重入的独占锁，具体可以对比ReentrantLock的实现方法
        if (compareAndSetState(0, 1)) {
            //继承自AbstractOwnableSynchronizer
            //保存当前的持有锁的线程
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    //释放锁设置state=0
    protected boolean tryRelease(int unused) {
        //设置独占锁线程为空
        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }

    public void lock() {
        acquire(1);
    } //最终会调用tryAcquire(1);

    public boolean tryLock() {
        return tryAcquire(1);
    }

    public void unlock() {
        release(1);
    } //最终会调用tryRelease(1);

    public boolean isLocked() {
        return isHeldExclusively();
    }
}