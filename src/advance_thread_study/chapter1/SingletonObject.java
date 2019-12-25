package advance_thread_study.chapter1;

public class SingletonObject {
    private static SingletonObject singletonObject =new SingletonObject();


    //私有化构造器
    private SingletonObject(){

    }

    public static SingletonObject getSingletonObject(){
        return singletonObject;
    }
}
