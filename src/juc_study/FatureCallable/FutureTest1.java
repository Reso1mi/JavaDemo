package juc_study.FatureCallable;


import java.util.concurrent.*;

/**
 * @author imlgw.top
 * @date 2019/8/6 19:46
 */
public class FutureTest1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor= (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

         Future<Integer> future= executor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                TimeUnit.SECONDS.sleep(3);
                for (int i=0;i>=0;i++){
                   System.out.println("??????????????");
                }
                return 111111;
            }
        });
        System.out.println("do some other thing");
        System.out.println(future.cancel(true));
    }
}
