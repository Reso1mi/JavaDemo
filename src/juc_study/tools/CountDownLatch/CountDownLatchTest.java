package juc_study.tools.CountDownLatch;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author imlgw.top
 * @date 2019/7/24 10:53
 */
public class CountDownLatchTest {

    private static Random random=new Random(System.currentTimeMillis());

    private static ExecutorService executor=Executors.newFixedThreadPool(2);

    private static  CountDownLatch latch;


    public static void main(String[] args) throws InterruptedException {
        int  []data=query();
        latch=new CountDownLatch(data.length);
        for (int i = 0; i <data.length ; i++) {
            executor.execute(new SimpleRunnable(data,i,latch));
        }
        latch.await();
        //也是个异步的
        executor.shutdown();
        System.out.println("all of works done ");

    }

    static class SimpleRunnable implements Runnable{
        private final int []data;
        private final int index;
        private final CountDownLatch latch;


        public SimpleRunnable(int[] data, int index, CountDownLatch latch) {
            this.data = data;
            this.index = index;
            this.latch=latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int value=data[index];
            if (value%2==0) {
                data[index]=2*value;
            }else{
                data[index]=10*value;
            }
            System.out.println(Thread.currentThread().getName()+" finished");
            latch.countDown();
        }
    }

    private static int[] query(){
        return new int[]{1,2,3,4,5,6,7,8,9,10};
    }
}
