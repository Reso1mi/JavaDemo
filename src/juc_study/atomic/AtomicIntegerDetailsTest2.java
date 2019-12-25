package juc_study.atomic;

/**
 * @author imlgw.top
 * @date 2019/4/22 16:56
 */
public class AtomicIntegerDetailsTest2 {
    private static CASLock lock = new CASLock();

    public static void main(String[] args) {
        for (int i = 0; i <5; i++) {
            new Thread(() -> {
                try {
                    try {
                        doSomething();
                    } catch (GetLockException e) {
                        System.out.println(Thread.currentThread().getName()+" don't get the lock");
                        //e.printStackTrace();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+" is to doSomething else");
            }).start();
        }
    }

    private static void doSomething() throws InterruptedException, GetLockException {
        try {
            lock.trylock();
            System.out.println(Thread.currentThread().getName() + " get the lock");
            Thread.sleep(100000);
        } finally {
            lock.unlock();
        }
    }
}
