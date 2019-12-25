package base_thread_study.chapter8;

public class Service2 {
    private Service1 service1;

    public Service2(Service1 service1) {
        this.service1 = service1;
    }

    private  final  Object LOCK=new Object();

    public  void  m1(){
        synchronized (LOCK){
            System.out.println("m1");
            service1.s1();
        }
    }

    public  void  m2(){
        synchronized (LOCK){
            System.out.println("m2");
        }
    }
}
