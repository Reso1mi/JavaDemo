package base_thread_study.chapter12;


import java.util.*;

public class SimpleThreadPool extends Thread {
    //线程池大小
    private int size;
    //线程大小变化值
    private int min;
    private int active;
    private int max;
    //默认值
    private final static int MIN = 4;
    private final static int ACTIVE = 8;
    private final static int MAX = 12;
    //任务队列大小
    private final int queueSize;

    public int getMin() {
        return min;
    }

    public int getActive() {
        return active;
    }

    public int getMax() {
        return max;
    }

    //线程池中线程编号
    private static volatile int seq = 0;
    //默认任务队列的大小
    private final static int DEFAULT_TASK_QUEUE_SIZE = 2000;

    //线程组
    private final static ThreadGroup GROUP = new ThreadGroup("Pool_Group");
    //线程名前缀
    private final static String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";
    //任务队列
    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();
    //线程队列
    private final static List<MyThread> THREAD_QUEUE = new ArrayList<>();
    //拒绝策略
    private final DiscardPolicy discardPolicy;
    //线程池是否销毁
    private volatile boolean destroy = false;
    //默认的拒绝策略（抛异常）
    public final static DiscardPolicy DEFAULT_DISCARD_POLICY = () -> {
        throw new DiscardException("Discard this Task!!!!(Default Policy)");
    };

    public SimpleThreadPool(int min, int active, int max, int queueSize, DiscardPolicy discardPolicy) {
        this.queueSize = queueSize;
        this.discardPolicy = discardPolicy;
        this.min = min;
        this.active = active;
        this.max = max;
        init();
    }

    public SimpleThreadPool() {
        this(MIN, ACTIVE, MAX, DEFAULT_TASK_QUEUE_SIZE, DEFAULT_DISCARD_POLICY);
    }

    //线程池的线程，维护整个线程
    @Override
    public void run() {
        while (!destroy) {
            System.err.printf("Pool#min:%d,active:%d,max:%d,currentSize:%d,taskRemain:%d\n",
                    this.min, this.active, this.max, this.size, TASK_QUEUE.size());
            try {
                Thread.sleep(5000);
                if (TASK_QUEUE.size() > active && size < active) {
                    for (int i = size; i < active; i++) {
                        createThreadQueue();
                    }
                    System.out.println("increment to active success");
                    this.size = active;
                } else if (TASK_QUEUE.size() > max && size < MAX) {
                    for (int i = size; i < max; i++) {
                        createThreadQueue();
                    }
                    System.out.println("increment to max success");
                    this.size = max;
                }
                if (TASK_QUEUE.isEmpty() && size > active) {
                    System.out.println("==================reduce=================");
                    //防止并发修改，在shutdown的时候reduce
                    synchronized (THREAD_QUEUE) {
                        int release = size - active;
                        //Iterator可以在遍历的过程中remove
                        for (Iterator<MyThread> it = THREAD_QUEUE.iterator(); it.hasNext(); ) {
                            if (release <= 0)
                                break;
                            MyThread mt = it.next();
                            //如果该线程在工作就不要打断它
                            if(mt.getThreadState()==ThreadState.RUNNING){
                                continue;
                            }
                            mt.close();
                            mt.interrupt();
                            it.remove();
                            release--;
                        }
                        this.size = active;
                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void init() {
        /*    for (int i = 0; i < size; i++) {
            createThreadQueue();
        }*/
        for (int i = 0; i < this.min; i++) {
            createThreadQueue();
        }
        this.size = min;
        this.start();
    }

    public void submit(Runnable runnable) {
        if (destroy) {
            throw new IllegalStateException("The Pool is shutdown , you can't submit now ! !");
        }
        synchronized (TASK_QUEUE) {
            if (TASK_QUEUE.size() > queueSize) {
                discardPolicy.discard();
            }
            TASK_QUEUE.addLast(runnable);
            //唤醒线程池中的线程
            TASK_QUEUE.notifyAll();
        }
    }

    public void shutdown() throws InterruptedException {
        while (!TASK_QUEUE.isEmpty()) {
            Thread.sleep(50);
        }
        int initVal = THREAD_QUEUE.size();
        synchronized (THREAD_QUEUE) {
            while (initVal > 0) {
                for (MyThread thread : THREAD_QUEUE) {
                    if (thread.getThreadState() == ThreadState.BLOCKED) {
                        //设置为DEAD状态
                        thread.close();
                        thread.interrupt();
                        initVal--;
                    } else {
                        Thread.sleep(10);
                    }
                }
            }
        }
        this.destroy = true;
        System.out.println("My Thread pool is shutdown");
    }

    public boolean isDestroy() {
        return this.destroy;
    }

    private void createThreadQueue() {
        MyThread thread = new MyThread(GROUP, THREAD_PREFIX + (seq++));
        thread.start();
        THREAD_QUEUE.add(thread);
    }


    private enum ThreadState {
        FREE, RUNNING, BLOCKED, DEAD
    }

    public static class DiscardException extends RuntimeException {
        public DiscardException(String message) {
            super(message);
        }
    }

    @FunctionalInterface
    public interface DiscardPolicy {
        void discard() throws DiscardException;
    }

    private static class MyThread extends Thread {
        private volatile ThreadState threadState = ThreadState.FREE;

        public ThreadState getThreadState() {
            return this.threadState;
        }

        public MyThread(ThreadGroup group, String name) {
            super(group, name);
        }

        @Override
        public void run() {
            OUTER:
            while (this.threadState != ThreadState.DEAD) {
                Runnable runnable;
                synchronized (TASK_QUEUE) {
                    while (TASK_QUEUE.isEmpty()) {
                        try {
                            this.threadState = ThreadState.BLOCKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            System.out.println(Thread.currentThread().getName() + " is dead");
                            break OUTER;
                        }
                    }
                    runnable = TASK_QUEUE.removeFirst();
                }
                Optional.of(runnable).ifPresent(t -> {
                    this.threadState = ThreadState.RUNNING;
                    t.run();
                    this.threadState = ThreadState.FREE;
                });
            }
        }

        private void close() {
            this.threadState = ThreadState.DEAD;
        }
    }
}
