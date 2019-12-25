package advance_thread_study.chapter11_threadLocal;

import java.util.HashMap;
import java.util.Map;

/**
 * @author imlgw.top
 * @date 2019/4/17 7:59
 */
public class ThreadLocalSimulator<T> {
    private final Map<Thread,T> threadMap=new HashMap<>();

    public void set(T t){
        Thread currentThread=Thread.currentThread();
        synchronized (threadMap){
            threadMap.put(currentThread,t);
        }
    }


    public T get(){
        Thread currentThread =Thread.currentThread();
        if(threadMap.get(currentThread)==null){
             threadMap.put(currentThread,initVal());
        }
        return threadMap.get(currentThread);
    }

    protected T initVal() {
        return null;
    }
}
