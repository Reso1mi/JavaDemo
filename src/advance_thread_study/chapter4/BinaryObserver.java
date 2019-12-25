package advance_thread_study.chapter4;

public class BinaryObserver extends Observer{

    public BinaryObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("Binarry String :"+ Integer.toBinaryString(subject.getState()));
    }
}
