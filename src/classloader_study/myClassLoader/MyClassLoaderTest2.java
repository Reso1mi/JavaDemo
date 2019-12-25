package classloader_study.myClassLoader;

import classloader_study.break_parent.SimpleClassLoader;

import java.lang.reflect.Method;

/**
 * @author imlgw.top
 * @date 2019/4/18 15:17
 */
public class MyClassLoaderTest2 {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        MyClassLoader loader1=new MyClassLoader("Resolmi-father");
        MyClassLoader loader2=new MyClassLoader("Resolmi2-son");
       // Class<?> aClass2 = loader2.loadClass("classloader_study.myClassLoader.MyObject");
        //Class<?> aClass1 = loader1.loadClass("classloader_study.myClassLoader.MyObject");
        //loader2.setDir("D:\\ClassLoaderTest2");
        //两个不同的加载器（没有父子关系）加载同一个类加载出来的不是同一个
        //System.out.println(aClass1.hashCode());
        //System.out.println(aClass2.hashCode());
        //System.out.println(aClass.getClassLoader());
        //System.out.println(((MyClassLoader)aClass.getClassLoader()).getClassLoaderName());

        //面试题? 怎么让static静态代码块执行两次
        SimpleClassLoader simpleClassLoader =new SimpleClassLoader();
        SimpleClassLoader simpleClassLoader2 =new SimpleClassLoader();
        Class.forName("classloader_study.myClassLoader.MyObject",true,simpleClassLoader);
        Class.forName("classloader_study.myClassLoader.MyObject",true,simpleClassLoader2);
     }
}
