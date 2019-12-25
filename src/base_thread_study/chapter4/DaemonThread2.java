package base_thread_study.chapter4;

public class DaemonThread2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            Thread inThread = new Thread(() -> {
                while (true) {
                    System.out.println("start heart check");
                    try {
                        Thread.sleep(1_00);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            //将子线程设置为守护线程
            inThread.setDaemon(true);
            inThread.start();
            try {
                Thread.sleep(1_000);
                System.err.println("Thread finish done...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
        //主线程(main)-->t-->inThread
    }
}
