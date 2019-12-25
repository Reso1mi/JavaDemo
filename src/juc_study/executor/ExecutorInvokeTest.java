package juc_study.executor;

import java.util.concurrent.*;

/**
 * @author imlgw.top
 * @date 2019/8/6 14:51
 */
public class ExecutorInvokeTest {

    private static int a=0;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService=Executors.newFixedThreadPool(10);

/*        Future<Integer> future= executorService.submit(() -> {
            TimeUnit.SECONDS.sleep(3);
            return 1000;
        });
        System.out.println(future.get());
        System.out.println("finish");*/


        executorService.execute(()->inc());
        executorService.execute(()->inc());
    }

    public static void inc(){
        while (a<500){
            System.out.println(Thread.currentThread().getName()+ ":"+a++);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
