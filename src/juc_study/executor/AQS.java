package juc_study.executor;

/**
 * @author imlgw.top
 * @date 2019/9/17 14:28
 */
public class AQS {
    private volatile int state;

    public void setState(int state) {
        this.state = state;
    }
}
