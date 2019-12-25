package base_thread_study.chapter7;

public class TicketRunnable implements Runnable {


    private final static int MAX_NO = 500;

    private int index = 1;

    private final Object Monitor = new Object();

    @Override
    public void run() {
        while (true) {
            synchronized (Monitor) {
                //单线程
                if (index > MAX_NO) {
                    return;
                }
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "第：" + index++);
            }
        }
    }
}
