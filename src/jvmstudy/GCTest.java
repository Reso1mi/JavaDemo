package jvmstudy;

/**
 * @author imlgw.top
 * @date 2019/8/15 17:16
 */
public class GCTest {
    private static int _1M= 1024*1024; //1m

    /*
        -XX:+PrintGCDetails
        -verbose:gc
        -Xms20M 限制Java堆大小20M不可扩展
        -Xmx20M
        -Xmn10M 10M分配给新生代
        -XX:SurvivorRatio=8
    */
    public static void main(String[] args) throws InterruptedException {
        byte[] alloc1,alloc2,alloc3,alloc4;
        alloc1=new byte[9*_1M];
        System.out.println("GC Test");
    }
}
