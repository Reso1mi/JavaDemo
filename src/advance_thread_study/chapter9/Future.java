package advance_thread_study.chapter9;

public interface Future<T> {
    T get() throws InterruptedException;
}
