package base_thread_study.chapter7;

public class SynchronizedTest {
    static  Object LOCK=new Object();
    public static void main(String[] args) {
        Runnable runnable=()->{
            synchronized (LOCK){
                try {
                    Thread.sleep(200_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t0=new Thread(runnable);
        Thread t1=new Thread(runnable);
        Thread t2=new Thread(runnable);
        t0.start();
        t1.start();
        t2.start();
    }
}
