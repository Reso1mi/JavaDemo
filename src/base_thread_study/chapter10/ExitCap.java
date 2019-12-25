package base_thread_study.chapter10;

public class ExitCap{
    public static void main(String []arg){
        int i=0;
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("The test app will shutdown");
            notifyAndRelease();
        }));
        while(true){
            try{
                Thread.sleep(1000);
                System.out.println("i am working");
            }catch(Exception e){
                //donothing
            }
            if(i>10){
                throw new RuntimeException();
            }
            i++;
        }
    }

    public static void notifyAndRelease(){
        System.out.println("notify to admin");

        try{
            Thread.sleep(1000);
        }catch(Exception e){}

        System.out.println("release the resources(socker. file, connection.)");

        try{
            Thread.sleep(1000);
        }catch(Exception e){}

        System.out.println("release and notify done");
    }
}
