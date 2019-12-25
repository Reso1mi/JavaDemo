package advance_thread_study.chapter3;

public class VolatileTest {
    private  static int INIT_VALUE = 0;
    private final static int MAX_LIMIT = 5;

    public static void main(String[] args) {
        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (localValue < MAX_LIMIT) {
                if (localValue != INIT_VALUE) {
                    System.out.println("The value update to " + INIT_VALUE);
                    localValue = INIT_VALUE;
                }
            }
        }, "Reader").start();

        new Thread(() -> {
            int localValue=INIT_VALUE;
            while (INIT_VALUE<MAX_LIMIT){
                System.out.println("Update the value to "+ ++localValue);
                INIT_VALUE=localValue;
                try {
                    //等一等reader
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Updater").start();
    }
}
