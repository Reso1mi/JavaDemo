package jvmstudy;

/**
 * @author imlgw.top
 * @date 2019/8/19 15:32
 */
public class ClassTest {

    public static void main(String[] args) {
        System.out.println(Sub.m);
    }

}

class  Sub extends MM implements FF{
    public static int m=10;

    public Sub(String name) {
        super(name);

    }
}

interface  FF{
     Thread THREAD=new Thread(){
         {
             System.out.println("FF is init");
         }
     };
}

class MM{
    public MM(String name){

    }
    static Thread  THREAD=new Thread(){
        {
            System.err.println("MM is init>>>>>>>>>>>>>");
        }
    };
}