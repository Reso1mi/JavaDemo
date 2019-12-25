package base_thread_study.chapter1;

public class ThreadDemo {

    public static void main(String[] args) {
        Thread test= new Thread(() -> System.out.println("imlgw"));
        Thread test1= new Thread(){
            @Override
            public void run() {

            }
        };
        //thread线程启动
        test.start();
        System.out.println("main执行完毕");
    }


    public  static  void  readData(){

    }

    public static  void  writeData(){

    }
}
