package base_thread_study.chapter6;

public class ThreadCloseGraceful2 {
    public static void main(String[] args) throws InterruptedException {
        Worker2 worker2 = new Worker2();
        worker2.start();
        Thread.sleep(5000);
        worker2.interrupt();
    }
}

class Worker2 extends Thread{
    //优雅的停止线程2-----打断
    @Override
    public void run() {
        while (true){
            /*try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break; //return 会直接退出
            }*/
            //代码有可能在执行isInterrupted之前就Block了
            if(isInterrupted()){
                break;
            }
        }
        //-----------
    }
}

