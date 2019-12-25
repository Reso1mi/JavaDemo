package base_thread_study.chapter7;

public class Bank2 {
    public static void main(String[] args) {
        //线程和逻辑业务分离的写法
        long start=System.currentTimeMillis();
        SynchronizeRunnable ticket=new SynchronizeRunnable();
        Thread t1=new Thread(ticket,"一号窗口");
        Thread t2=new Thread(ticket,"二号窗口");
        Thread t3=new Thread(ticket,"三号窗口");
        t1.start();
        t2.start();
        t3.start();
        System.out.println(System.currentTimeMillis()-start);
    }
}
