package advance_thread_study.chapter1;

public class SingletonObjectPlus {
    /**
     * 饿汉形式的单例
     */

    private static  SingletonObjectPlus singletonObjectPlus =null;

    //私有化构造器
    private SingletonObjectPlus(){

    }

    //存在线程安全问题
    public static SingletonObjectPlus getSingletonObject1(){
    	if(singletonObjectPlus==null){
    		return new SingletonObjectPlus();
    	}
        return singletonObjectPlus;
    }

    //存在性能问题
    public synchronized static SingletonObjectPlus getSingletonObject2(){
        if(singletonObjectPlus==null){
            return new SingletonObjectPlus();
        }
        return singletonObjectPlus;
    }

    //double check 可能会空指针？？？重排序？
    // private static volatile  SingletonObjectPlus singletonObjectPlus =null;
    public static SingletonObjectPlus getSingletonObject3(){
        if(singletonObjectPlus==null){
            synchronized(SingletonObject.class){
                if(singletonObjectPlus==null){
                    return new SingletonObjectPlus();
                }
            }
        }
        return singletonObjectPlus;
    }
}
