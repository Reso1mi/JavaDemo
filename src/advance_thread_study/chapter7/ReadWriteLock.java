package advance_thread_study.chapter7;

public class ReadWriteLock {
    private int readingR = 0;
    private int waitingR = 0;
    private int writingW = 0;
    private int waitingW = 0;

    public synchronized void readLock() throws InterruptedException{
        this.waitingR++;
        try {
            //如果有线程在写就不能读
            while (writingW > 0) {
                this.wait();
            }
            this.readingR++;
        }finally {
            this.waitingR--;
        }
    }

    public synchronized void readUnlock() throws InterruptedException{
        this.readingR--;
        notifyAll();
    }


    public synchronized void writeLock() throws InterruptedException{
        this.waitingW++;
        try {
            while (readingR>0||writingW>0){
                this.wait();
            }
            this.writingW++;
        }finally {
            this.waitingW--;
        }
    }

    public synchronized void writeUnlock() throws InterruptedException{
        this.writingW--;
        notifyAll();
    }

}
