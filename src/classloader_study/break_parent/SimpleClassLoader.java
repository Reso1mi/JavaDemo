package classloader_study.break_parent;

import juc_study.atomic.UnsafeTest;
import sun.misc.Unsafe;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SimpleClassLoader extends ClassLoader {

    private final static String DEFAULT_DIR = "D:\\ClassLoaderTest";

    private String dir = DEFAULT_DIR;

    private String classLoaderName;

    public SimpleClassLoader() {
        super();
    }

    public SimpleClassLoader(String classLoaderName) {
        super();
        this.classLoaderName = classLoaderName;
    }

    public SimpleClassLoader(String classLoaderName, ClassLoader parent) {
        super(parent);
        this.classLoaderName = classLoaderName;
    }


    /**
     * xxx.xxx.xxx.xxx.AAA
     * xxx/xxx/xxx/xxx/AAA.class
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name)
            throws ClassNotFoundException {
        Unsafe unsafe=UnsafeTest.getUnsafe();

        String classPath = name.replace(".", "/");
        File classFile = new File(dir, classPath + ".class");
        if (!classFile.exists()) {
            throw new ClassNotFoundException("The class " + name + " found under " + dir);
        }

        byte[] classBytes = loadClassBytes(classFile);
        if (null == classBytes || classBytes.length == 0)
            throw new ClassNotFoundException("load the class " + name + " failed");

        //return unsafe.defineClass(null,classBytes,0,classBytes.length,SimpleClassLoader.this,null);
        return this.defineClass(name, classBytes, 0, classBytes.length);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve)
            throws ClassNotFoundException {
        Class<?> clazz = null;

        //java核心的包
        if (name.startsWith("java.")) {
            try {
                ClassLoader system = ClassLoader.getSystemClassLoader();
                //这里仍然是委托上级
                clazz = system.loadClass(name);
                if (clazz != null) {
                    if (resolve)
                        resolveClass(clazz);
                    return clazz;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            clazz = findClass(name);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (clazz == null && getParent() != null) {
            getParent().loadClass(name);
        }

        return clazz;
    }

    private byte[] loadClassBytes(File classFile) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             FileInputStream fis = new FileInputStream(classFile)) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getClassLoaderName() {
        return classLoaderName;
    }
}