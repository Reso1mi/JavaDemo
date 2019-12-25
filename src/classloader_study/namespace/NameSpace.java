package classloader_study.namespace;

import classloader_study.break_parent.SimpleClassLoader;
import classloader_study.break_parent.SimpleObject;

/**
 * @author imlgw.top
 * @date 2019/4/20 16:02
 */
public class NameSpace {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        SimpleClassLoader loader1=new SimpleClassLoader();
        Class<?> aClass = loader1.loadClass("classloader_study.break_parent.SimpleObject");
        SimpleObject simpleObject= (SimpleObject) aClass.newInstance();
    }
}
