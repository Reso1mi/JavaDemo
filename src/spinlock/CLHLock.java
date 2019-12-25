package spinlock;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class CLHLock {
    public static class CLHNode {
        private volatile boolean block= true; // 默认是在等待锁
    }

    private volatile CLHNode tail ;

    private static final AtomicReferenceFieldUpdater<CLHLock, CLHNode> UPDATER = AtomicReferenceFieldUpdater
            . newUpdater(CLHLock.class, CLHNode .class , "tail" );

    public void lock(CLHNode currentThreadNode) {
        //获取之前的尾结点, 然后将自己设置为尾节点
        CLHNode preNode = UPDATER.getAndSet( this, currentThreadNode);
        if(preNode != null) {//已有线程占用了锁，进入自旋
            while(preNode.block ) {
            }
        }
    }

    public void unlock(CLHNode currentThreadNode) {
        // 如果队列里只有当前线程，则释放对当前线程的引用（for GC）。
        // 尝试设置尾节点为自己, 传入的期望值是自己,成功就代表队列中只有它一个
        if (!UPDATER.compareAndSet(this, currentThreadNode, null)) {
            // 还有后续线程
            currentThreadNode.block = false ;// 改变状态，让后续线程结束自旋
        }
    }
}