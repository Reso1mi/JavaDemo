package base_thread_study.chapter4;

public class DeamonThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t=new Thread(()->{
            //新建状态
            try {
                System.out.println(Thread.currentThread().getName()+"running");
                Thread.sleep(100000);
                System.out.println(Thread.currentThread().getName()+"down");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //runnable可执行状态（就绪状态） ===> 有可能进入running ==> dead ==> block
        t.setDaemon(true); //设置成后台线程后main线程结束而结束
        t.start();
        Thread.sleep(10_000); //jdk1.7
        //到这里main线程已经结束了
        System.out.println(Thread.currentThread().getName());
    }
}
