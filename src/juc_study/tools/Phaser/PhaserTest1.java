package juc_study.tools.Phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @author imlgw.top
 * @date 2019/7/30 13:44
 */
public class PhaserTest1 {
    public static void main(String[] args) {
        final Phaser phaser = new Phaser();

        Stream.of("t1", "t2", "t3", "t4", "t5").forEach(name -> new Thread(new Task(phaser), name).start());
        //注册main线程
        phaser.register();
        phaser.arriveAndAwaitAdvance();
        System.out.println("all of work finished");
    }

    static class Task implements Runnable {
        private final Phaser phaser;

        Task(Phaser phaser) {
            this.phaser = phaser;
            //动态增加
            this.phaser.register();
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " is working");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //
            phaser.arriveAndAwaitAdvance();
        }
    }
}
