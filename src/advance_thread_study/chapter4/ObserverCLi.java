package advance_thread_study.chapter4;

import java.util.Stack;

/**
 * 观察者模式
 */
public class ObserverCLi {
    public static void main(String[] args) {
        final Subject subject=new Subject();
        BinaryObserver binary=new BinaryObserver(subject);
        OctalOberver octalOberver = new OctalOberver(subject);
        System.out.println("==================");
        subject.setState(10);
        System.out.println("==================");
        subject.setState(10);
        Stack stack=new Stack();
    }
}
