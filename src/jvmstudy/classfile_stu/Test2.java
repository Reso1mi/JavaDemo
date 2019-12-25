package jvmstudy.classfile_stu;

import java.util.List;

/**
 * @author imlgw.top
 * @date 2019/9/2 22:09
 */
public class Test2 {

    private static int bbbb = 99;

    private final static int aaaa = 99;

    private List<Integer> list=null;

    public Test2(int a) {
        bbbb = a;
    }

    public int inc() throws ArithmeticException{
        int x;
        try {
            x=1;
            return  x;
        }catch (Exception e){
            x=2;
            return x;
        }finally {
            x=3;
        }
    }

    @Deprecated
    public void deprecatedMethod(){

    }
}