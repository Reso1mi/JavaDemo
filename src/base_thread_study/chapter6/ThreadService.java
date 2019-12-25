package base_thread_study.chapter6;

public class ThreadService {
    private  Thread executeThread;

    private  volatile  boolean finished=false;

    public void execute(Runnable task){
        executeThread =new Thread(()->{
            //子线程
            Thread t=new Thread(()->{
                task.run();
            });
            t.setDaemon(true);
            t.start();
            try {
                t.join(); //这里阻塞的是executeThread
                finished=true;
                //到这里说明executeThread已经不阻塞了,子线程已经执行完了，没有超时
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("task execution was interrupted");
            }
        });
        executeThread.start();
    }

    public void shutdown(long mills){
        long base=System.currentTimeMillis();
        while (!finished){ //轮询检查标志位，看是否已经结束
            if(System.currentTimeMillis()-base>=mills){
                //超时了没有完成
                System.out.println("TLE, will kill it");
                executeThread.interrupt(); //打断executeThread
                break;
            }
            //没超时
            try {
                executeThread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("executeThread was interrupted when shutdown");
            }
        }
        finished=false;
    }
}
