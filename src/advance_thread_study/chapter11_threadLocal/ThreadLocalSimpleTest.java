package advance_thread_study.chapter11_threadLocal;

/**
 * @author imlgw.top
 * @date 2019/4/16 22:46
 */
public class ThreadLocalSimpleTest {
    //线程本地变量
    private static  ThreadLocal<String> threadLocal=new ThreadLocal(){
        @Override
        protected Object initialValue() {
            return "imlgw.top";
        }
    };

    public static void main(String[] args) throws InterruptedException {
        threadLocal.set("imlgw.top");
        Thread.sleep(500);
        System.out.println(threadLocal.get());
    }
}
