package jvmstudy;

/**
 * @author imlgw.top
 * @date 2019/8/11 10:28
 */
public class SynchnorizedTest {

    private static int a=0;

    public static void main(String[] args) {
        System.out.println(a--);
        System.out.println("synchronized block is start");
        synchronized (SynchnorizedTest.class){
            System.out.println(a++);
        }
        System.out.println("synchronized block is end");

    }

}
