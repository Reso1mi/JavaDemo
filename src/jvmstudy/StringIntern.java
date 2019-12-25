package jvmstudy;
/**
 * @author imlgw.top
 * @date 2019/8/11 16:59
 */
public class StringIntern {
    public static void main(String[] args) {
        //s1 s2存放在局部变量表中
        // abc 存放在常量池中（字节码常量）
        String s1="abc";
        String s22="abc";

        System.out.println(s1==s22);
        //new出来的一定是在堆内存中
        String str=new String("abc");
        System.out.println(s1==str);
        //运行时常量
        System.out.println(s1==str.intern()); //
        //颠覆认知的
        System.out.println(str==str.intern()); //false

        System.out.println("=========================");

        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);

        String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);
    }
}
