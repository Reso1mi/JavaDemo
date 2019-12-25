package base_thread_study.chapter9;

/**
 *  测试notify的时间
 *  代码很傻逼，奇怪的问题
 */
public class TestNotifyTime {
    public static void main(String[] args) throws InterruptedException {
        TestNotifyTime testNotifyTime = new TestNotifyTime();
        new Thread(()->{
            testNotifyTime.m2();
        }).start();
        Thread.sleep(1000);
        new Thread(()->{
            testNotifyTime.m1();
        }).start();
    }

    public  synchronized void m1(){
        System.out.println("m1 ready notify m2");
        this.notify();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public  synchronized  void m2(){
        try {
            System.out.println("m2 is wait");
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2 is notify");
    }
}
