package advance_thread_study.chapter5;

public class ObservableRunnable implements Runnable{

    final protected LifeCycleListener lifeCyclelistener;

    public ObservableRunnable(LifeCycleListener lifeCyclelistener) {
        this.lifeCyclelistener = lifeCyclelistener;
    }

    //通知观察者
    protected void notifyChange(RunnableEvent event){
            lifeCyclelistener.onEvent(event);
    }
    public enum RunnableState{
        RUNNING,ERROR,DONE;
    }

    public static class RunnableEvent{
        private final RunnableState state;
        private final Thread thread;

        public Thread getThread() {
            return thread;
        }

        public Throwable getCause() {
            return cause;
        }

        public RunnableState getState() {

            return state;
        }

        private final Throwable cause;

        public RunnableEvent(RunnableState state, Thread thread, Throwable cause) {
            this.state = state;
            this.thread = thread;
            this.cause = cause;
        }
    }
    @Override
    public void run() {

    }
}
