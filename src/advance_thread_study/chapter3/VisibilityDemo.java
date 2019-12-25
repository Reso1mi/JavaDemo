package advance_thread_study.chapter3;

public class VisibilityDemo {
    public static void main(String[] args) throws InterruptedException {
        TimeConsumingTask timeConsumingTask = new TimeConsumingTask();
        new Thread(timeConsumingTask).start();
        Thread.sleep(10);
        timeConsumingTask.cancel();
    }
}

class TimeConsumingTask implements Runnable{
    private  boolean isCancel=false;

    private volatile  int a;
    @Override
    public void run() {
        while (!isCancel){

        }
    }



    public void cancel(){
        isCancel=true;
        System.out.println("is cancel.....");
    }
}