package base_thread_study.chapter7;

public class SynchonizedDetail {

    private final Object LOCK=new Object();

    public synchronized void m1() {
        System.out.println("同步方法");
    }

    public void m2() {
        synchronized (LOCK) {
            System.out.println("同步代码块");
        }
    }
}
