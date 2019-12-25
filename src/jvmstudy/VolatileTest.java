package jvmstudy;

/**
 * @author imlgw.top
 * @date 2019/8/16 9:53
 */
public class VolatileTest {
    private static volatile int var=10;
    public static void main(String[] args) {
        //write
        var=100;
        System.out.println("=====================");
        //read
        int localval=var;
    }
}
