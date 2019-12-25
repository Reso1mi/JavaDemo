package advance_thread_study.chapter3;

public class VolatileTest2 {
    private volatile static int INIT_VALUE = 0;
    private static int MAX_LIMIT = 500;

    public static void main(String[] args) {
        new Thread(() -> {
            while (INIT_VALUE< MAX_LIMIT) {
                System.out.println("The value update to " + ++INIT_VALUE);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Adder1").start();

        new Thread(() -> {
            while (INIT_VALUE < MAX_LIMIT) {
                System.out.println("The value update to " + ++INIT_VALUE);
                try {
                    Thread.sleep(11);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Adder2").start();
    }
}
