package classloader_study.misc;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author imlgw.top
 * @date 2019/4/18 11:14
 */
public class ClinitThreadTest {
    public static void main(String[] args) {
        new Thread(()->new SimpleObj()).start();
        new Thread(()->new SimpleObj()).start();
        new Thread(()->new SimpleObj()).start();
    }

    static class SimpleObj{
        private static AtomicBoolean init=new AtomicBoolean(true);

        static {
            System.out.println(Thread.currentThread().getName()+" i will be init");
            while (init.get()){

            }
            System.out.println("i am finished");
        }
    }
}
