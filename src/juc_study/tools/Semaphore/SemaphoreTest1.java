package juc_study.tools.Semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author imlgw.top
 * @date 2019/7/25 15:48
 */
public class SemaphoreTest1 {
    public static void main(String[] args) {
        final SemaphoreLock semaphoreLock=new SemaphoreLock();

        new Thread(()->{
            try {
                semaphoreLock.lock();
                System.out.println(Thread.currentThread().getName()+" get the lock");
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphoreLock.unlock();
                System.out.println(Thread.currentThread().getName()+"release the lock");
            }

        }).start();


        new Thread(()->{
            try {
                semaphoreLock.lock();
                System.out.println(Thread.currentThread().getName()+" get the lock");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphoreLock.unlock();
                System.out.println(Thread.currentThread().getName()+"release the lock");
            }

        }).start();
    }

    static class SemaphoreLock{
        private final  Semaphore semaphore =new Semaphore(1);

        private void lock() throws InterruptedException {
            semaphore.acquire();
        }

        public void unlock(){
            semaphore.release();
        }
    }
}
