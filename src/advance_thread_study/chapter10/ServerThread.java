package advance_thread_study.chapter10;

import java.util.Random;

/**
 * @author imlgw.top
 * @date 2019/4/16 20:35
 */
public class ServerThread extends Thread{
    private final RequestQueue queue;

    private final Random random;

    private volatile boolean closed = false;

    public ServerThread(RequestQueue queue) {
        this.queue = queue;
        random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        while (!closed) {
            Request request = queue.getRequest();
            //get可能会返回null
            if (null == request) {
                System.out.println("Received the empty request.");
                break;
            }
            System.out.println("Server ->" + request.getValue());
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                //打断后直接return
                return;
            }
        }
    }

    public void close() {
        this.closed = true;
        this.interrupt();
    }
}
