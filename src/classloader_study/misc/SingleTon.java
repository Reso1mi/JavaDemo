package classloader_study.misc;

/**
 * @author imlgw.top
 * @date 2019/4/17 20:34
 */
public class SingleTon {
    private static SingleTon ins=new SingleTon();

    public static  int x=0;

    public static int y;


    private SingleTon(){
        x++;y++;
    }

    public static SingleTon getIns(){
        return ins;
    }

    public static void main(String[] args) {
        SingleTon singleTon=getIns();
        System.out.println(singleTon.x);
        System.out.println(singleTon.y);
    }
}
