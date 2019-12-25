package classloader_study.misc;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author imlgw.top
 * @date 2019/4/17 20:12
 */
public class ClassActiveUse {
    static {
        System.out.println("main is init");
    }
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //System.out.println(Obj.t);
        //Obj.getObj();
        //Class<?> aClass = Class.forName("classloader_study.misc.Obj", false, ClassActiveUse.class.getClassLoader());
        //aClass.newInstance();
        //System.out.println(ObjChild.age);

        // System.out.println(ObjChild.n); //父类初始化 子类不初始化 通过子类调用父类的静态变量
        Obj [] arrays=new Obj[10]; //不会初始化 定义应用数组也不会初始化类
        System.out.println(arrays.getClass());
        //System.out.println(ObjChild.t); //也不会初始化 常量会在编译期间放到常量池中不会初始化类
        //4. System.out.println(Obj.x);  //会被初始化    final修饰的复杂化类型再编译期间无法计算得到，会初始化类
    }
}

class Obj{

    public static  final int t=10;  //编译期间就已经确定了就是10

    public  static  int n=111;
    public static final int x=new Random().nextInt(100); //值不是常量，运行期间才会确定
    static {
        System.out.println("Obj is init");
    }

    static void getObj(){
        System.out.println("NULL");
    }
}

class ObjChild extends Obj{
    public static int age=12;

    static {
        System.out.println("Child is init");
    }
}
interface I{
    final static int a=0;

}
