package classloader_study.encryption_study;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author imlgw.top
 * @date 2019/4/19 20:23
 */
public final class EncryptUtil {

    public static final byte ENCRYPT_FACTOR = (byte) 0xff;

    private EncryptUtil() {

    }

    public static void encrypt(String source, String target) throws FileNotFoundException {
        try (FileInputStream in= new FileInputStream(source); FileOutputStream out = new FileOutputStream(target)) {
            int data;
            while ((data=in.read())!=-1){
                out.write(data^ENCRYPT_FACTOR);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        encrypt("D:\\ClassLoaderTest\\classloader_study\\myClassLoader\\MyObject.class","D:\\ClassLoaderTest\\classloader_study\\myClassLoader\\MyObject2.class");
    }
}

