package juc_study.atomic;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author imlgw.top
 * @date 2019/4/22 14:43
 */
public class AtomicIntegerTest {

    /**
     * 1.保证可见性
     * 2.有序性
     */
    private  static volatile /*int */ AtomicInteger val=new AtomicInteger();

    public static void main(String[] args) {
 /*       new Thread(()->{
            int x=0;
            while (x<500){
                int temp=val.get();
                System.out.println(Thread.currentThread().getName()+" "+temp);
                val.incrementAndGet();
                x++;

            }
        },"t1").start();
        new Thread(()->{
            int x=0;
            while (x<500){
                int temp=val.get();
                System.out.println(Thread.currentThread().getName()+" "+temp);
                val.incrementAndGet();
                x++;
            }
        },"t2").start();*/
        val.addAndGet(10);
        val.compareAndSet(10,20);
        System.out.println(val.get());
    }
}
