package advance_thread_study.chapter7;

public class Client {
    public static void main(String[] args) {
        final SharedData sharedData = new SharedData(10);
        new Reader(sharedData).start();
        new Reader(sharedData).start();
        new Reader(sharedData).start();
        new Reader(sharedData).start();
        new Reader(sharedData).start();
        new Writer(sharedData, "qwertyuiopasdfg").start();
        new Writer(sharedData, "QWERTYUIOPASDFG").start();
    }
}
