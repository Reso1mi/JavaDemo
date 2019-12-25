package base_thread_study.chapter8;

public class DeadLockTest {
    public static void main(String[] args) {
        Service1 service1=new Service1();
        Service2 service2 =new Service2(service1);
        service1.setService2(service2);

        new Thread(()->{
            while (true){
                service2.m1();
            }
        }).start();

        new Thread(()->{
            while (true){
                service1.s2();
            }
        }).start();
    }
}
