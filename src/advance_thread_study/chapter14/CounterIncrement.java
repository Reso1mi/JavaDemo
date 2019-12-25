package advance_thread_study.chapter14;

/**
 * @author imlgw.top
 * @date 2019/4/17 18:37
 */
public class CounterIncrement extends Thread {
    private volatile boolean terminated = false;

    private int counter=0;

    @Override
    public void run() {
        try {
            while (!terminated) {
                System.out.println(Thread.currentThread().getName()+" "+counter++);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.clean();
        }
    }

    public void clean() {
        System.out.println("clean "+counter);
    }
}
