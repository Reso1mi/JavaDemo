package jvmstudy.class_executor;

/**
 * @author imlgw.top
 * @date 2019/9/10 19:00
 */
public class JVMStack {

    public static int calculate(){
        int a=1;
        int b=2;
        int c=3;
        int d=4;
        int result=(a+b-c)*d;
        return  result;
    }

    public static void main(String[] args) {
        int a=1;
        int b=2;
        swap(a,b);
        System.out.println(a+","+b);
    }

    public static void swap(int a,int b){
        int temp=a;
        a=b;
        b=temp;
    }
}

/*class Int{
    int value;
    public Int(int value){
        this.value=value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}*/
