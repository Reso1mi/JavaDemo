package classloader_study.myClassLoader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author imlgw.top
 * @date 2019/4/18 12:57
 */
public class MyClassLoader extends ClassLoader {
    private static final String DEFAULT_DIR = "D:\\ClassLoaderTest";

    private String dir = DEFAULT_DIR;

    private String classLoaderName;

    public MyClassLoader() {

    }

    public MyClassLoader(String classLoaderName) {
        super();
        this.classLoaderName = classLoaderName;
    }

    public MyClassLoader(ClassLoader parent, String classLoaderName) {
        super(parent);
        this.classLoaderName = classLoaderName;
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

    /**
     * xx.xx.xx.xx.xx.AAA
     * xx/xx/xx/xx/xx/.AAA.class
     *
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("findclass is invoke MyClassLoader is load"+name);
        String classPath = name.replace(".", "/");
        File classFile = new File(dir, classPath + ".class");
        if (!classFile.exists()) {
            throw new ClassNotFoundException("The class " + name + " not found under " + dir);
        }

        byte[] classBytes = loadClassBytes(classFile);
        if (null == classBytes || classBytes.length == 0)
            throw new ClassNotFoundException("load the class " + name + " failed");

        return this.defineClass(name, classBytes, 0, classBytes.length);
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
}
