package juc_study.tools.ForkJoin;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @author imlgw.top
 * @date 2019/7/30 13:08
 */
public class RecursiveActionTest1 {

    private final static int MAX_THRESHOLD=3;

    private static AtomicInteger SUM=new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        final ForkJoinPool forkJoinPool=new ForkJoinPool();
        forkJoinPool.submit(new CalculateRecursiveAction(0, 100));
        TimeUnit.SECONDS.sleep(3);
        System.out.println(SUM.get());
    }

    private static class CalculateRecursiveAction extends RecursiveAction{

        private final int start;
        private final int end;

        private CalculateRecursiveAction(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {

            if(end-start<=MAX_THRESHOLD){
                SUM.addAndGet(IntStream.rangeClosed(start,end).sum());
            }else{
                int mid=(start+end)/2;
                CalculateRecursiveAction leftTask=new CalculateRecursiveAction(start,mid);
                CalculateRecursiveAction rightTask=new CalculateRecursiveAction(mid+1,end);
                //阻塞
                leftTask.fork();
                rightTask.fork();
            }
        }
    }

}
