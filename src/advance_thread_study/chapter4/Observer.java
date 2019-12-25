package advance_thread_study.chapter4;

public  abstract class Observer {
    protected Subject subject;

    public Observer(Subject subject){
        this.subject=subject;
        subject.attach(this);
    }

    public  abstract  void update();
}
