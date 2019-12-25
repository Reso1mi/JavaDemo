package advance_thread_study.chapter9;

/**
 * Future   ->未来的票据
 * FutureTask   ->实际执行的任务
 * FutureService  ->桥接Future和FutureTask
 */
public class SyncInvoker {
    public static void main(String[] args) throws InterruptedException {

        FutureService futureService = new FutureService();
        Future<String> submit = futureService.submit(() -> {
            try {
                Thread.sleep(10000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "FINISH";
        },System.out::println);

        System.out.println("===========");
        System.out.println(" do other thing.");
        Thread.sleep(1000);
        System.out.println("===========");
    }

    private static String get()
            throws InterruptedException {
        Thread.sleep(10000l);
        return "FINISH";
    }
}
