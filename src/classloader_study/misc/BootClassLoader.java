package classloader_study.misc;

/**
 * @author imlgw.top
 * @date 2019/4/18 11:45
 */
public class BootClassLoader {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("-----------------");
        //根加载器
        System.out.println(System.getProperty("sun.boot.class.path"));
        //扩展加载器
        System.out.println("-----------------");
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));
        Class<?> aClass = Class.forName("classloader_study.misc.SimpleObject");
        System.out.println(aClass.getClassLoader()); //AppClassLoader
        System.out.println(aClass.getClassLoader().getParent()); //ExtClassLoader
        System.out.println(aClass.getClassLoader().getParent().getParent()); //null

        //java.lang.String
        //java.lang.String s=new java.lang.String();
        //s.getVal();
        Class<?> aClass1 = Class.forName("java.lang.StringABCD");
        System.out.println(aClass1);
        System.out.println(aClass1.getClassLoader());
    }
}
