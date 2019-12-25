package advance_thread_study.chapter10;

/**
 * @author imlgw.top
 * @date 2019/4/16 20:43
 */
public class SuspensionClient {
    public static void main(String[] args) throws InterruptedException {
        final RequestQueue queue = new RequestQueue();
        new ClientThread(queue, "Shaw").start();
        ServerThread serverThread = new ServerThread(queue);
        serverThread.start();
        //serverThread.join(); join住了后面还咋close？？？
        Thread.sleep(10000);
        serverThread.close();
    }
}
