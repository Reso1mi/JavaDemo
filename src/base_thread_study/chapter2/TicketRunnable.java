package base_thread_study.chapter2;

public class TicketRunnable implements  Runnable {


    private  final static  int MAX_NO=50;

    private    int index=1;

    @Override
    public void run() {
        while (index <= MAX_NO) {
            System.out.println(Thread.currentThread().getName() + "第：" + index++);
        }
    }
}
