package juc_study.atomic;

import sun.misc.Unsafe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.HashSet;

/**
 * @author imlgw.top
 * @date 2019/5/2 15:00
 */
public class UnsafeFooTest {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
         Unsafe unsafe = UnsafeTest.getUnsafe();
        /*Class.forName("juc_study.atomic.atomic.UnsafeFooTest$Simple",true,ClassLoader.getSystemClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(UnsafeFooTest.class.getClassLoader());
        System.out.println(System.class.getClassLoader());*/

/*        Simple simple = (Simple) unsafe.allocateInstance(Simple.class);
        System.out.println(simple.get());
        System.out.println(simple.getClass().getClassLoader());*/

/*        Permission permission = new Permission();

        permission.doSth();
        //通过反射也可以做到，但是unsafe直接是到内存地址中将值修改了
        Field access_allow = permission.getClass().getDeclaredField("ACCESS_ALLOW");
        unsafe.putLong(permission,unsafe.objectFieldOffset(access_allow),-1);
        permission.doSth();*/

        byte[] bytes = loadClassContent();
        Class<?> aClass = unsafe.defineClass(null, bytes, 0, bytes.length, ClassLoader.getSystemClassLoader(), null);
        int get = (int) aClass.getMethod("get").invoke(aClass.newInstance(), null);
        System.out.println("unsafe加载类获取get:"+get);
        //System.out.println(sizeof(new Simple()));
    }

    public static long sizeof(Object object) {
        Unsafe unsafe = UnsafeTest.getUnsafe();
        //去重去掉父类的
        HashSet<Field> fields = new HashSet<>();
        Class c = object.getClass();
        while (c != Object.class) {
            Field[] declaredFields = c.getDeclaredFields();
            for (Field declaredField : declaredFields)
                //非静态字段
                if ((Modifier.STATIC & declaredField.getModifiers())==0) {
                    fields.add(declaredField);
                }
                //父类的Class
            c=c.getSuperclass();
        }
        //最大偏移量
        long maxOffSize = 0;
        for (Field field : fields) {
            maxOffSize = maxOffSize > unsafe.objectFieldOffset(field) ? maxOffSize : unsafe.objectFieldOffset(field);
        }
        return (maxOffSize/8+1)*8;
    }

    static class Simple {
        //private String a = "a";
        int a=0;
        public Simple() {
          //  a = "new";
        }

       /* public String get() {
            return a;
        }*/

    }

    public static byte[] loadClassContent() {
        File f = new File("D:\\ClassLoaderTest\\Res.class");
        FileInputStream stream = null;
        byte[] bytes = null;
        try {
            stream = new FileInputStream(f);
            bytes = new byte[(int) f.length()];
            stream.read(bytes);
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}

class Permission {
    private int ACCESS_ALLOW = 0;

    private boolean isAllow() {
        return ACCESS_ALLOW == -1;
    }

    public void doSth() {
        if (isAllow()) {
            System.out.println("i am workind");
        }
    }
}
