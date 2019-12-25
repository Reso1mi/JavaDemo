package jvmstudy.classfile_stu;

/**
 * @author imlgw.top
 * @date 2019/8/28 18:21
 */
public class Test1 {

    private int a;

    public int getA(){
        return a;
    }

    public void setA(int a) throws ArithmeticException{
        try {
            this.a=a;
        }catch (NullPointerException e){
            this.a=1;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Test1().a);
    }
}
