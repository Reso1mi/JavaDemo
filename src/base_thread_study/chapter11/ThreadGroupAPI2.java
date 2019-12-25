package base_thread_study.chapter11;

import java.util.Arrays;

public class ThreadGroupAPI2 {
    public static void main(String[] args) {
        ThreadGroup tgp = new ThreadGroup("TGP1");
        Thread t = new Thread(tgp, "t0") {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("t0");
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        };
        t.start();

        ThreadGroup tgp2 = new ThreadGroup(tgp,"TGP2");
        Thread t1 = new Thread(tgp2, "t1") {
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
        //评估活跃的线程
        System.out.println(tgp.activeCount());
        System.out.println(tgp.activeGroupCount());
        //当前线程有没有修改该线程组的权利，没有回抛异常
        t1.checkAccess();
        //销毁空的线程组，不为空报错
        //tgp.destroy();
        Thread[] threads=new Thread[tgp.activeCount()];
        //将tgp内的thread拷贝到threads数组
        tgp.enumerate(threads);
        Arrays.asList(threads).forEach(System.out::println);
    }
}
