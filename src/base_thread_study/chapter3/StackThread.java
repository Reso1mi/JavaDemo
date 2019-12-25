package base_thread_study.chapter3;

public class StackThread {
    public static void main(String[] args) {
        Thread t=new Thread(null,()->{
            try {
                add();
            }catch (Error error){
                error.printStackTrace();
                System.out.println(count);
            }

        },"Test"); //10k
        t.start();
    }

    private static  int count;
    public static  void add(){
        count++;
        add();
    }
}
