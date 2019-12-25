package base_thread_study.chapter5;

public class ThreadJoin3 {
    public static void main(String[] args) throws InterruptedException {
        long l1 = System.currentTimeMillis();
        Thread t0 = new Thread(new CaptureMachine("M0", 1000));
        Thread t1 = new Thread(new CaptureMachine("M1", 2000));
        Thread t2 = new Thread(new CaptureMachine("M2", 4000));
        t0.start();
        t1.start();
        t2.start();
        t0.join();
        t1.join();
        t2.join();
        long l = System.currentTimeMillis();
        System.out.println("end save begin timestamp:" + l1 + "end timestamp" + l);
    }
}

class CaptureMachine implements Runnable {
    private String machineId;

    private long spentTime;

    public CaptureMachine(String machineId, long spentTime) {
        this.machineId = machineId;
        this.spentTime = spentTime;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(spentTime);
            System.out.println(machineId + " capture done");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
