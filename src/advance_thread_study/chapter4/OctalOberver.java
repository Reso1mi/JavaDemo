package advance_thread_study.chapter4;

public class OctalOberver extends Observer{
    public OctalOberver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("Octal String:"+Integer.toOctalString(subject.getState()));
    }
}
