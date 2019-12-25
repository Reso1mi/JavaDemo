package spinlock;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author imlgw.top
 * @date 2019/8/10 21:49
 */
public class MCSLock {
    public static class MCSNode {
        //持有后继者的引用
        volatile MCSNode next;
        // 默认是在等待锁
        volatile boolean block = true;
    }

    volatile MCSNode tail;// 指向最后一个申请锁的MCSNode

    private static final AtomicReferenceFieldUpdater<MCSLock, MCSNode> UPDATER = AtomicReferenceFieldUpdater
            .newUpdater(MCSLock.class, MCSNode.class, "tail");

    public void lock(MCSNode currentThreadMcsNode) {
        //更新tail为最新加入的线程节点，并取出之前的节点（也就是当前节点的前驱）
        MCSNode predecessor = UPDATER.getAndSet(this, currentThreadMcsNode);
        if (predecessor != null) {
            //连接在tail的尾部
            predecessor.next = currentThreadMcsNode;
            //轮询自己的isLocked属性
            while (currentThreadMcsNode.block) {

            }
        } else {
            //前驱节点为空直接获取锁,自己是第一个
            currentThreadMcsNode.block = false;
        }
    }

    public void unlock(MCSNode currentThreadMcsNode) {
        if (currentThreadMcsNode.block) {
            return;
        }
        //这个时候可能会有其他线程又加入了进来 Double Check
        if (currentThreadMcsNode.next == null) {
            //CAS 将tail设置为空
            if (UPDATER.compareAndSet(this, currentThreadMcsNode, null)) {
                // 设置成功返回，没有其他线程等待锁
                return;
            } else {
                //CAS更新tail失败,有线程抢先一步执行lock更新了tail
                //但是可能还没有连接在 之前的tail(当前节点)后
                while (currentThreadMcsNode.next == null) {
                    //等待 predecessor.next = currentThreadMcsNode执行
                    //否则后面会报NPE
                }
            }
        }
        //修改后继者的isLocked,通知后继者结束自旋
        currentThreadMcsNode.next.block = false;
        currentThreadMcsNode.next = null;// for GC
    }
}
