package base_thread_study.chapter8;

public class Service1 {
    private  final Object LOCK=new Object();

    private Service2 service2;

    public  void s1(){
        synchronized (LOCK){
            System.out.println("s1==============");
        }
    }

    public void s2(){
        synchronized (LOCK){
            System.out.println("s2==============");
            service2.m2();
        }
    }

    public void setService2(Service2 service2) {
        this.service2 = service2;
    }
}
