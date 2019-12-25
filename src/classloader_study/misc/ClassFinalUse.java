package classloader_study.misc;

import juc_study.FatureCallable.TestInterFace;

import java.util.Random;

/**
 * @author imlgw.top
 * @date 2019/8/19 17:27
 */
public class ClassFinalUse {
    public static void main(String[] args) {
        //System.out.println(TestFinal.str);
        TestFinal [] arr=new TestFinal[10];
        Object [] a=new Object[1];
        System.out.println(a.getClass());
        //System.out.println(TestInterfaceSon.b);
    }

}
class TestFinal{
    public  static  final String str="hello world";
    static {
        System.out.println("TestFinal is init");
        System.out.println(TestFinal.class);
    }
}

interface TestInterface{
     int a=0;
}

interface TestInterfaceSon extends TestInterFace {
    int b=new Random().nextInt();
}
