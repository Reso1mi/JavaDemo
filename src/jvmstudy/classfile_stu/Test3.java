package jvmstudy.classfile_stu;
/**
 * @author imlgw.top
 * @date 2019/9/5 16:17
 */
public class Test3 {
    String str="Welcome";
    private  int x=5;

    public static Integer in=10;

    public static void main(String[] args) {
        Test3 test3 = new Test3();
        test3.setX(0);
        in=20;
    }

    private void setX(int i) {
        this.x=i;
    }
}
