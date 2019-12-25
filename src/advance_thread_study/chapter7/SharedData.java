package advance_thread_study.chapter7;

public class SharedData {
    private final char[] buffer;
    private final ReadWriteLock lock = new ReadWriteLock();

    public SharedData(int size) {
        this.buffer = new char[size];
        for (int i = 0; i < buffer.length; i++) {
            this.buffer[i] = '*';
        }
    }

    public char[] read() throws InterruptedException {
        try {
            lock.readLock();
            return doRead();
        } finally {
            lock.readUnlock();
        }
    }

    public void write(char c) throws InterruptedException {
        try {
            lock.writeLock();
            doWrite(c);
        } finally {
            lock.writeUnlock();
        }
    }

    private void doWrite(char c) {
        for (int i = 0; i <buffer.length; i++) {
            buffer[i]=c;
            slowly(10);
        }
    }

    private char[] doRead() {
        char[] newBuff = new char[buffer.length];
        for (int i = 0; i < buffer.length; i++) {
            newBuff[i] = buffer[i];
        }
        slowly(50);
        return newBuff;
    }

    private void slowly(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
