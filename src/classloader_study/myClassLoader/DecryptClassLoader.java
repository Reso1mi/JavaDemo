package classloader_study.myClassLoader;

import classloader_study.encryption_study.EncryptUtil;

import java.io.*;

/**
 * @author imlgw.top
 * @date 2019/4/19 20:51
 */
public class DecryptClassLoader extends ClassLoader {

    private final String DEFAULT_DIR = "D:\\ClassLoaderTest";

    private String dir = DEFAULT_DIR;

    public DecryptClassLoader() {
        super();
    }

    public DecryptClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
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
        try (FileInputStream in = new FileInputStream(classFile); ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            int data;
            while ((data=in.read())!=-1){
                baos.write(data^0xff);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setDir(String dir) {
        this.dir = dir;
    }
}
