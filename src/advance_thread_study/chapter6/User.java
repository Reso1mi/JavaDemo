package advance_thread_study.chapter6;

public class User implements Runnable {
    private final  String userName;
    private  final String userAddress;

    private final  Gate gate;

    public User(String userName, String userAddress, Gate gate) {
        this.userName = userName;
        this.userAddress = userAddress;
        this.gate = gate;
    }

    @Override
    public void run() {
        System.out.println(userName+" BEGIN");
        while (true){
            gate.pass(userName,userAddress);
        }
    }
}
