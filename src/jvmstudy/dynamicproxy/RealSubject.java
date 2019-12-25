package jvmstudy.dynamicproxy;

/**
 * @author imlgw.top
 * @date 2019/9/11 10:22
 */
public class RealSubject implements Subject{

    @Override
    public void request() {
        System.out.println("real Subject");
    }
}
