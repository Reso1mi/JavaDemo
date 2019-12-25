package juc_study.tools.ForkJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

/**
 * @author imlgw.top
 * @date 2019/7/30 11:58
 */
public class RecursiveTaskTest1 {

    private final static int MAX_THRESHOLD=3;

    public static void main(String[] args) {
        final ForkJoinPool forkJoinPool=new ForkJoinPool();
        ForkJoinTask<Integer> submit = forkJoinPool.submit(new CaculateRecursiveTask(0, 100));
        try {
            Integer integer = submit.get();
            System.out.println(integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static class CaculateRecursiveTask extends RecursiveTask<Integer>{

        private final int start;
        private final int end;

        private CaculateRecursiveTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {

            if(end-start<=MAX_THRESHOLD){
                return IntStream.rangeClosed(start,end).sum();
            }else{
                int mid=(start+end)/2;
                CaculateRecursiveTask leftTask=new CaculateRecursiveTask(start,mid);
                CaculateRecursiveTask rightTask=new CaculateRecursiveTask(mid+1,end);
                //阻塞
                leftTask.fork();
                rightTask.fork();
                //返回结果
                return leftTask.join()+rightTask.join();
            }
        }
    }

}
