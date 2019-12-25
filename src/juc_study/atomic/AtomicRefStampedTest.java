package juc_study.atomic;

import java.lang.ref.Reference;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author imlgw.top
 * @date 2019/4/25 14:39
 */
public class AtomicRefStampedTest {
   static AtomicStampedReference<Integer> reference=new AtomicStampedReference<>(100,0);

    public static void main(String[] args) {
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("t1 "+reference.compareAndSet(100, 101, reference.getStamp(), reference.getStamp()+1));
                System.out.println(reference.getStamp()+","+reference.getReference());
                System.out.println("t1 "+ reference.compareAndSet(101, 100,reference.getStamp() , reference.getStamp()+1));
            } catch (InterruptedException e) {

            }

        }).start();


        new Thread(()->{
            try {
                int stamp = reference.getStamp();
                //假设执行到这里发生上下文切换
                System.out.println("Before sleep:stamp="+stamp);
                TimeUnit.SECONDS.sleep(5);
                System.out.println("After sleep:stamp="+reference.getStamp());
                boolean b = reference.compareAndSet(100, 101, stamp, stamp + 1);
                System.out.println(b);
            } catch (InterruptedException e) {

            }
        }).start();

    }
}
