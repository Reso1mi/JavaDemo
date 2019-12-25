package jvmstudy.classfile_stu;

/**
 * @author imlgw.top
 * @date 2019/9/5 17:29
 */
public class Test4 {
    public static Integer a = 0;

    public void setA(int x) {
        synchronized (this) {
            a = x;
        }
    }
}
