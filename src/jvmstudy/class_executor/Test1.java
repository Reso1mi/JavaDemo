package jvmstudy.class_executor;

/**
 * @author imlgw.top
 * @date 2019/9/7 8:52
 */
public class Test1 {
    public static void test(){
        System.out.println("static test() invoke");
    }

    public final  void test2(){
        System.out.println("static test() invoke");
    }

    public static void main(String[] args) {

        test();
        new Test1().test2();
    }
}
