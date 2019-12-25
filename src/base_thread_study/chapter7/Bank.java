package base_thread_study.chapter7;

public class Bank {
    public static void main(String[] args) throws InterruptedException {
        /*
        耦合方式
        TicketWindow t1=new TicketWindow("一号窗口");
        t1.start();
        TicketWindow t2=new TicketWindow("二号窗口");
        t2.start();
        TicketWindow t3=new TicketWindow("三号窗口");
        t3.start();
        */

        //线程和逻辑业务分离的写法
        long start=System.currentTimeMillis();
        TicketRunnable ticket=new TicketRunnable();
        Thread t1=new Thread(ticket,"一号窗口");
        Thread t2=new Thread(ticket,"二号窗口");
        Thread t3=new Thread(ticket,"三号窗口");
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println(System.currentTimeMillis()-start);
    }
}
