package juc_study.executor;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

/**
 * @author imlgw.top
 * @date 2019/8/5 20:23
 */
public class TestAQSLock {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        Worker worker = new Worker();
        Worker worker2 = new Worker();
        new Thread(()->test(worker),"T0").start();
        new Thread(()->test(worker2),"T1").start();

        System.out.println("let it go");
        System.out.println("===================");
/*
        Work work = new Work();
        Work work2 = new Work();
        work.setState(13);
        work2.setState(213);
        Field state = work.getClass().getSuperclass().getDeclaredField("state");
        state.setAccessible(true);
        System.out.println(state.getInt(work));

        Field state2 = work.getClass().getSuperclass().getDeclaredField("state");
        state2.setAccessible(true);
        System.out.println(state.getInt(work2));*/
    }

    public  static  void test(Worker worker){
        worker.lock();
        System.out.println(Thread.currentThread().getName()+" is running");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        worker.unlock();
    }
}
