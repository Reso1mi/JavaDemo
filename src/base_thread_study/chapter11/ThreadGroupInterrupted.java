package base_thread_study.chapter11;

import java.util.Arrays;

public class ThreadGroupInterrupted {
    public static void main(String[] args) {
        ThreadGroup tgp = new ThreadGroup("tgp");
        Thread t0 = new Thread(tgp, "t0") {
            @Override
            public void run() {
                //while (true) {
                    try {
                        //System.out.println("t0");
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                //}
            }
        };
        //tgp.setDaemon(true);
        t0.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(tgp.isDestroyed());
        tgp.destroy();
        System.out.println(tgp.isDestroyed());

        //ThreadGroup subtgp = new ThreadGroup(tgp,"subtgp");
        /*Thread t1 = new Thread(subtgp , "t1") {
            @Override
            public void run() {
                while (true){
                    try {
                        System.out.println("t1");
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();
        subtgp.interrupt();*/
    }
}
