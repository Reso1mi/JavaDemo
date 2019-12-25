package advance_thread_study.chapter5;

import java.util.List;

public class ThreadLIfeCycleObserver implements LifeCycleListener {
    private final Object LOCK = new Object();

    @Override
    public void onEvent(ObservableRunnable.RunnableEvent event) {
        synchronized (LOCK){
            System.out.println("The runnable [" + event.getThread().getName() + "] data changed and state is [" + event.getState() + "]");
            if(event.getCause()!=null){
                System.out.println("The Thread process "+ event.getThread().getName()+" fail");
                event.getCause().printStackTrace();
            }
        }
    }

    public void concurrentQuery(List<String> ids) {
        if (ids == null || ids.isEmpty())
            return;
        ids.stream().forEach(id -> new Thread(new ObservableRunnable(this) {
            @Override
            public void run() {
                try {
                    notifyChange(new RunnableEvent(RunnableState.RUNNING, Thread.currentThread(), null));
                    System.out.println("Query for the id " + id);
                    Thread.sleep(1000);
                    notifyChange(new RunnableEvent(RunnableState.DONE, Thread.currentThread(), null));
                } catch (InterruptedException e) {
                    notifyChange(new RunnableEvent(RunnableState.ERROR, Thread.currentThread(), e));
                    e.printStackTrace();
                }
            }
        }).start());
    }
}
