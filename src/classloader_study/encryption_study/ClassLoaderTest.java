package classloader_study.encryption_study;

import classloader_study.myClassLoader.DecryptClassLoader;
import classloader_study.myClassLoader.MyClassLoader;

/**
 * @author imlgw.top
 * @date 2019/4/19 21:27
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader classLoader=new MyClassLoader();
        //DecryptClassLoader classLoader=new DecryptClassLoader();
        classLoader.setDir("D:\\ClassLoaderTest\\");
        Class<?> aClass = classLoader.loadClass("classloader_study.myClassLoader.MyObject");
        System.out.println(aClass);
        System.out.println(aClass.getClassLoader());
    }
}
