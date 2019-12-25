package base_thread_study.chapter6;

public class ThreadCloseGraceful {
    public static void main(String[] args) throws InterruptedException {
        Worker worker=new Worker();
        worker.start();
        Thread.sleep(10000);
        worker.shutdown();
    }
}

class Worker extends Thread{
    //优雅的停止线程-----开关
    private volatile boolean start = true;

    @Override
    public void run() {
        while (start){

        }
    }

    public void shutdown(){
        this.start=false;
    }
}
