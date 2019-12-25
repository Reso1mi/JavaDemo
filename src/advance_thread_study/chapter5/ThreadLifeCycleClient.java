package advance_thread_study.chapter5;

import java.util.Arrays;

public class ThreadLifeCycleClient {
    public static void main(String[] args) {
        new ThreadLIfeCycleObserver().concurrentQuery(Arrays.asList("1","2"));
    }
}
