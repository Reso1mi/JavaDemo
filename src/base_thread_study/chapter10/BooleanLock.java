package base_thread_study.chapter10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class BooleanLock implements Lock {

    //false indicated free
    private boolean initValue;

    //加锁的线程
    private Thread lockedThread;

    private Collection<Thread> blockThreadCollection = new ArrayList<>();

    public BooleanLock() {
        this.initValue = false;
    }

    @Override
    public synchronized void lock() throws InterruptedException {
        while (initValue) {
            blockThreadCollection.add(Thread.currentThread());
            System.out.println(Thread.currentThread().getName() + " is wait");
            this.wait();
        }
        blockThreadCollection.remove(Thread.currentThread());
        this.initValue = true;
        this.lockedThread = Thread.currentThread();
    }

    @Override
    public synchronized void lock(long time) throws InterruptedException, TimeOutException {
        if (time <= 0) lock();
        long remainTime=time;
        long endTime=System.currentTimeMillis()+time;
        while (initValue){
            if(remainTime<=0){
                throw new TimeOutException("time is out");
            }
            blockThreadCollection.add(Thread.currentThread());
            this.wait(time);
            remainTime=endTime-System.currentTimeMillis();
        }
        this.initValue=true;
        this.lockedThread=Thread.currentThread();
        blockThreadCollection.remove(Thread.currentThread());
    }

    @Override
    public synchronized void unLock() {
        //判断是不是加锁的线程
        if (lockedThread == Thread.currentThread()) {
            this.initValue = false;
            this.notifyAll();
            Optional.of(Thread.currentThread().getName() + "  release the lock monitor").ifPresent(System.out::println);
        }
    }

    @Override
    public Collection<Thread> getBlockThread() {
        return Collections.unmodifiableCollection(blockThreadCollection);
    }
}
