package base_thread_study.chapter6;

public class ThreadCloseForce {
    public static void main(String[] args) {
        ThreadService service=new ThreadService();
        long start=System.currentTimeMillis();
        service.execute(()->{
            //load a heavy resource
            while (true){

            }
        });
        service.shutdown(5000);
        long end=System.currentTimeMillis();
        System.out.println(end-start);
    }
}
