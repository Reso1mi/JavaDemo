package advance_thread_study.Chapter8;

public class UsePersonThread extends Thread{
    private Person person;

    public UsePersonThread(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        while (true){
            System.out.println(Thread.currentThread().getName()+" print " + person);
        }
    }
}
