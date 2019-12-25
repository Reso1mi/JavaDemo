package advance_thread_study.chapter1;

import java.util.stream.IntStream;

/**
 * 枚举的方式
 */
public class SingleGraceful2 {
    private SingleGraceful2() {

    }

    private enum Singleton {
        INSTANCE;

        private final SingleGraceful2 instance;

         Singleton() {
            instance = new SingleGraceful2();
        }

        public SingleGraceful2 getInstance() {
            return instance;
        }
    }

    public static SingleGraceful2 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    //测试一下
    public static void main(String[] args) {
        IntStream.rangeClosed(1, 100).forEach(i -> new Thread(String.valueOf(i)) {
            @Override
            public void run() {
                System.out.println(SingleGraceful2.getInstance());
            }
        }.start());
    }
}
