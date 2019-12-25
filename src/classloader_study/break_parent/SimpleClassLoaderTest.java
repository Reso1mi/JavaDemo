package classloader_study.break_parent;

public class SimpleClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        SimpleClassLoader simpleClassLoader = new SimpleClassLoader();
        //Class<?> aClass = simpleClassLoader.loadClass("classloader_study.break_parent.SimpleObject");
        Class<?> aClass = simpleClassLoader.loadClass("java.lang.String");
        System.out.println(aClass.getClassLoader());
    }
}
