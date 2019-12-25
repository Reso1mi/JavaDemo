package classloader_study.encryption_study;

/**
 * @author imlgw.top
 * @date 2019/4/19 18:48
 */
public class SimpleEncrypt {
    private static final String plain="Hello ClassLoader";

    private static final byte ENCRYPT_FACTOR=(byte)0xfc;
    public static void main(String[] args) {
        byte[] bytes=plain.getBytes();
        /*for (byte aByte : bytes) {
            System.out.print(aByte);//721011081081113267108971151157611197100101114
        }*/
        byte[] encrypt=new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            encrypt[i]= (byte) (bytes[i]^ENCRYPT_FACTOR);
        }
        System.out.println(new String(encrypt));
        byte[] decrypt=new byte[encrypt.length];
        for (int i = 0; i < encrypt.length; i++) {
            decrypt[i]= (byte) (encrypt[i]^ENCRYPT_FACTOR);
        }
        System.out.println(new String(decrypt));
    }
}
