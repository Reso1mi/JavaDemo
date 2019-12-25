package base_thread_study.chapter6;

public class ThreadInterrup {

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        System.out.println("收到打断信号");
                        //这里还有一个;
                        //Thread.interrupted(); 和下面的方法一样主要是为了实现Runnable接口的方式设计的
                        //底层调用的都是一个方法返回的值时一样的但是
                        //Thread.interrupted() 会清除标志位为 false
                        System.out.println(Thread.interrupted());
                        e.printStackTrace();
                    }
                    System.out.println("--->" + this.isInterrupted());
                }
            }
        };

        t.start();
        Thread.sleep(100);
        System.out.println(t.isInterrupted());
        t.interrupt();
        System.out.println(t.isInterrupted());
        //true 但是线程仍让在运行
        //t.stop();
    }
}
