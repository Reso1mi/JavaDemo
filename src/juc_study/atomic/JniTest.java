package juc_study.atomic;

/**
 * @author imlgw.top
 * @date 2019/5/1 12:32
 */
public class JniTest {

    public static void main(String[] args) {
        new JniTest().hi();
    }

    static {
        System.out.println("Hello");
    }

    private native void hi();
}
