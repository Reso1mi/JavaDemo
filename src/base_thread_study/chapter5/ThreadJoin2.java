package base_thread_study.chapter5;

public class ThreadJoin2 {
    public static void main(String[] args) throws InterruptedException {
        /*Thread t0 = new Thread(() -> {
            try {
                Optional.of("t0 task is running").ifPresent(System.out::println);
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Optional.of("t0 task is done").ifPresent(System.out::println);
        });
        t0.start();
        t0.join(1000);
        Optional.of("all task is done!!!!").ifPresent(System.out::println);
        IntStream.range(1,1000).forEach(i -> System.out.println(Thread.currentThread().getName() + "-->" + i));*/


        //自己等待自己
        Thread.currentThread().join();
    }
}
