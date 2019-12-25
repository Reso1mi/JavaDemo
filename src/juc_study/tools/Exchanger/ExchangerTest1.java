package juc_study.tools.Exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author imlgw.top
 * @date 2019/7/25 13:54
 */
public class ExchangerTest1 {
    public static void main(String[] args) {
        final Exchanger<String> exchanger=new Exchanger<>();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+" start");
            try {
                String res = exchanger.exchange("i am start From A",10,TimeUnit.SECONDS);
                System.out.println("back msg=" +res);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"done");
        }).start();


        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+" start");
            try {
                String res = exchanger.exchange("i am start from B");
                System.out.println("back msg=" +res);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"done");
        });
    }
}
