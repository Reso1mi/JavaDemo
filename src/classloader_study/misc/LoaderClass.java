package classloader_study.misc;

/**
 * @author imlgw.top
 * @date 2019/4/17 21:42
 */
public class LoaderClass {
    public static void main(String[] args) {
    /*    Resolmi resolmi0 = new Resolmi();
        Resolmi resolmi2 = new Resolmi();
        Resolmi resolmi1 = new Resolmi();
        Resolmi resolmi3 = new Resolmi();
        Resolmi resolmi4 = new Resolmi();
        System.out.println(resolmi0.getClass()==resolmi1.getClass());
        System.out.println(resolmi3.getClass()==resolmi2.getClass());
        System.out.println(resolmi4.getClass()==resolmi1.getClass());
        System.out.println(Resolmi.x);*/
        //System.out.println(Son.x);
        //Resolmi.te();
    }
}

class Resolmi {
    public static int x=10000;
    static {
        System.out.println(x);
        x++;
        y=1; //只能进行赋值不能读
        //System.out.println(y);
    }
    public static int y=2000;

    public  static  void te(){
        System.out.println(y);
    }
}

class Father{
    static {
        System.out.println("父类");
    }
}
class Son extends Father{
    public static int x=100;

    static {
        System.out.println("子类");
    }
}