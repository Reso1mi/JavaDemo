package base_thread_study.chapter2;

public class TicketWindow extends Thread {

    private final String name;

    private static int MAX_NO = 50;

    private static int index = 1;

    public TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (index <= MAX_NO) {
            System.out.println(name + "第：" + index++);
        }
    }

}
