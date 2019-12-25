package base_thread_study.chapter6;

public class ThreadInterrup2 {
    public static void main(String[] args) {
        Thread main=Thread.currentThread();

        Thread t=new Thread(()->{
            while (true){

            }
        });
        t.start();

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //t.interrupt(); 这里打断的是t线程阻塞的是main线程所以打断不了，捕获不到异常
            main.interrupt();
            System.out.println("打断 main 线程");
        });

        t2.start();

        try {
            //这里阻塞的是main线程不是t线程所以打断不了
            t.join();
        } catch (InterruptedException e) {
            System.out.println(main.isInterrupted());
            e.printStackTrace();
        }
    }
}
