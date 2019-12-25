package advance_thread_study.chapter6;

public class Client {
    public static void main(String[] args) {
        Gate gate=new Gate();
        User bj=new User("Beibei","Beijing",gate);
        User sh=new User("Shanglao","Shanghai",gate);
        User gz=new User("Guanglao","Guangzhou",gate);
        new Thread(bj).start();
        new Thread(sh).start();
        new Thread(gz).start();
    }
}
