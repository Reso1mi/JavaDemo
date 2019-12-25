package advance_thread_study.chapter13_countDown;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * @author imlgw.top
 * @date 2019/4/17 16:52
 */
public class JDKCountDown {

    private static final Random random = new Random(System.currentTimeMillis());


    public static void main(String[] args) throws InterruptedException {
        //final CountDownLatch latch=new CountDownLatch(5);
        final CountDown latch=new CountDown(5);

        System.out.println("准备多线程处理任务");
        //the first phase
        IntStream.range(0,5).forEach(i->{
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+" is working");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.down();
            },String.valueOf(i)).start();
        });
        latch.await();
        //the second phase
        System.out.println("多线程任务全部结束，准备第二阶段任务");
        System.out.println("........");
        System.out.println("Finish");
    }
}
