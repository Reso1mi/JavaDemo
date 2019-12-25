package base_thread_study.chapter10;

import java.util.Collection;

public interface Lock {

    class TimeOutException extends Exception {
        public TimeOutException(String message) {
            super(message);
        }
    }

    void lock() throws InterruptedException;

    void lock(long time) throws InterruptedException,TimeOutException;

    void unLock() throws InterruptedException;

    Collection<Thread> getBlockThread();
}
