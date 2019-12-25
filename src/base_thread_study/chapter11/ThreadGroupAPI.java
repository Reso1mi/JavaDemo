package base_thread_study.chapter11;

import java.util.Arrays;

public class ThreadGroupAPI {
    public static void main(String[] args) {
        ThreadGroup tgp = new ThreadGroup("TGP1");
        Thread t = new Thread(tgp, "t0") {
            @Override
            public void run() {
                while (true) {
                    try {
                        /*System.out.println(getThreadGroup().getName());
                        System.out.println(getThreadGroup().getParent());
                        //可以访问，文档上说不行
                        System.out.println(getThreadGroup().getParent().activeCount());*/
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();

        ThreadGroup tgp2 = new ThreadGroup("TGP2");
        Thread t1 = new Thread(tgp2, "t1") {
            @Override
            public void run() {
                System.out.println(tgp.getName());
                System.out.println(tgp.activeCount());
                Thread[] threads=new Thread[tgp.activeCount()];
                tgp.enumerate(threads);
                //也可以访问
                Arrays.asList(threads).forEach(System.out::println);
            }
        };
        t1.start();

        System.out.println(tgp2.getName());
        System.out.println(tgp2.getParent().getName());
    }
}
