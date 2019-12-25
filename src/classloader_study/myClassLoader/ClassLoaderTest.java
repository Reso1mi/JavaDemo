package classloader_study.myClassLoader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author imlgw.top
 * @date 2019/4/18 13:44
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyClassLoader loader=new MyClassLoader("Resolmi");
        Class<?> aClass = loader.loadClass("classloader_study.myClassLoader.MyObject");
        
        //MyObject myObject= (MyObject) aClass.newInstance();
        //System.out.println(myObject);
    }
}
