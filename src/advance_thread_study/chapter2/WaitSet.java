package advance_thread_study.chapter2;

import java.util.stream.Stream;

public class WaitSet {
    private static final Object LOCK=new Object();

    public static void main(String[] args) {
        Stream.of("t0", "t1", "t2", "t3", "t4", "t5", "t6", "t7", "t8", "t9").forEach(name -> {
            new Thread(name) {
                @Override
                public void run() {
                    synchronized (LOCK) {
                        try {
                            System.out.println(Thread.currentThread().getName()+" is in the waitSet");
                            LOCK.wait();
                            System.out.println(Thread.currentThread().getName()+" is out the waitSet");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        });
    }
}
