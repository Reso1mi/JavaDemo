package base_thread_study.chapter11;

public class ThreadException {
    private static int A = 10;
    private static int B = 0;

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int res = A / B;
        }
        );
        thread.start();
        thread.setUncaughtExceptionHandler((t, e) -> {
            System.out.println(t.getName());
            System.out.println(e);
        });
    }
}
