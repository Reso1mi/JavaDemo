package classloader_study.thread_context_classloader;

import classloader_study.myClassLoader.MyClassLoader;

/**
 * @author imlgw.top
 * @date 2019/4/20 17:43
 */
public class ThreadContextClassLoader {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader context=Thread.currentThread().getContextClassLoader();
        System.out.println(context);

        Thread.currentThread().setContextClassLoader(new MyClassLoader());
        System.out.println(Thread.currentThread().getContextClassLoader());

        Class.forName("com.mysql.jdbc.Driver");
    }
}
