package advance_thread_study.chapter1;

public class SingleGraceful {
    private SingleGraceful(){

    }

    private static class InstanceHolder{
        //只会被初始化一次
        private final static SingleGraceful instance=new SingleGraceful();
    }

    public static SingleGraceful getInstance(){
        return SingleGraceful.InstanceHolder.instance;
    }
}
