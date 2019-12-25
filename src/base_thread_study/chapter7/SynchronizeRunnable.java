package base_thread_study.chapter7;

public class SynchronizeRunnable implements Runnable {

    private final static int MAX_NO = 500;

    private int index = 1;

    @Override
    public  void run() {
        //this锁
        while (true) {
            if (ticket()) {
                return;
            }

        }
    }

    private boolean ticket(){
        synchronized (this) {
            //1.getFiled
            if (index > MAX_NO) {
                return true;
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // getFiled index
            // index=index+1
            // putFiled index
            //同步代码块就是保护共享数据index, MAX_NO不是,他是只读数据
            System.out.println(Thread.currentThread().getName() + "第：" + index++);
            return false;
        }
    }
}
