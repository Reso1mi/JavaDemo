package base_thread_study.chapter1;

public class ThreadDemo2 {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            System.out.println("Runnable");
        }) {
            @Override
            public void run() {
                System.out.println("Thread");
            }
        };
        t.start();
    }
}
