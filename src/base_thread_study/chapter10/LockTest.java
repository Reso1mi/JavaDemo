package base_thread_study.chapter10;

import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

public class LockTest {
    public static void main(String[] args) {
        final BooleanLock booleanLock = new BooleanLock();
        Stream.of("t0", "t1", "t2").forEach(name -> {
            new Thread(() -> {
                try {
                    booleanLock.lock(10);
                    Optional.of(Thread.currentThread().getName() + " get the lock").ifPresent(System.out::println);
                    doSomething();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Lock.TimeOutException e) {
                    System.out.println(Thread.currentThread().getName()+" Time out");
                    e.printStackTrace();
                } finally {
                    booleanLock.unLock();
                }
            }, name).start();
        });
        //main线程释放锁，不应该，谁加的锁应该由谁去释放锁
        //booleanLock.unLock();
    }

    private static ReentrantLock lock = new ReentrantLock();

    private static void doSomething() {
        Optional.of(Thread.currentThread().getName() + " is working...").ifPresent(System.out::println);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
