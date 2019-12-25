package advance_thread_study.chapter11_threadLocal;

import java.util.Random;

/**
 * @author imlgw.top
 * @date 2019/4/17 7:50
 */
public class ThreadLocalComplexTest {

    //private final static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    private final static ThreadLocalSimulator<String> threadLocal = new ThreadLocalSimulator(){
        @Override
        protected Object initVal() {
            return "默认值";
        }
    };

    //seed
    private final static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            threadLocal.set("Thread-T1");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            threadLocal.set("Thread-T2");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("============");
        System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
    }
}
