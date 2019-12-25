package spinlock;

/**
 * @author imlgw.top
 * @date 2019/8/11 11:02
 */
public class CLHLock2 {
    private  static class CLHNode{
        public volatile boolean locked=false;
    }

    private ThreadLocal<CLHNode> preNode=new ThreadLocal<>();

}
