package advance_thread_study.chapter13_countDown;

/**
 * @author imlgw.top
 * @date 2019/4/17 17:03
 */
public class CountDown {
    private final int total;

    //计数器
    private int counter;

    public CountDown(int total) {
        this.total = total;
    }

    public void down(){
        synchronized (this){
            this.counter++;
            this.notifyAll();
        }
    }

    public void await() throws InterruptedException {
        synchronized (this){
            while (counter!=total){
                this.wait();
            }
        }
    }
}
