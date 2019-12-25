package juc_study.tools.Exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * @author imlgw.top
 * @date 2019/7/25 15:14
 */
public class ExchangerTest2 {
    public static void main(String[] args) {
        final Exchanger<Simple> exchanger=new Exchanger<>();
        new Thread(()->{
            Simple simple = new Simple(1);
            System.out.println(Thread.currentThread().getName()+" start. send to B"+simple);
            try {
                Simple res = exchanger.exchange(simple);
                //休眠10s
                TimeUnit.SECONDS.sleep(3);
                System.out.println("A receive from B obj:"+res +" data:"+res.a);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" done");
        },"A").start();

        new Thread(()->{
            Simple simple = new Simple(2);
            System.out.println(Thread.currentThread().getName()+" start. send to A:"+simple);
            try {
                Simple res = exchanger.exchange(simple);
                //修改发送出去的obj
                simple.setA(100000);
                System.out.println("B receive from A obj:"+res +" data:"+res.a);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" done");
        },"B").start();
    }

    static class Simple{
        private int a;

        public Simple(int a) {
            this.a = a;
        }

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }
    }
}
