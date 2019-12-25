package advance_thread_study.chapter10;

import java.util.LinkedList;

/**
 * @author imlgw.top
 * @date 2019/4/16 20:27
 */
public class RequestQueue {
    private final LinkedList<Request> queue = new LinkedList<>();

    public Request getRequest() {
        synchronized (queue) {
            while (queue.size() <= 0) {
                try {
                    //队列为空等一下
                    queue.wait();
                } catch (InterruptedException e) {
                    return null;
                }
            }
            Request request = queue.removeFirst();
            return request;
        }
    }

    public void putRequest(Request request) {
        synchronized (queue) {
            queue.addLast(request);
            queue.notifyAll();
        }
    }
}
