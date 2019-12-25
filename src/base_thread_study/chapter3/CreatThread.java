package base_thread_study.chapter3;

import java.util.Arrays;

public class CreatThread {
    public static void main(String[] args) {
        Thread t=new Thread(()->{
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
        System.out.println(t.getThreadGroup());
        //Thread t2=new Thread("das")
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        System.out.println(threadGroup .getName());
        System.out.println(threadGroup.activeCount());

        Thread [] threads=new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
        //java8
        Arrays.asList(threads).forEach(System.out::println);
    }
}
