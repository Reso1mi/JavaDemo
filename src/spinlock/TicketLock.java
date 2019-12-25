package spinlock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author imlgw.top
 * @date 2019/8/10 21:05
 */
public class TicketLock {
    private AtomicInteger serviceNum = new AtomicInteger(); // 服务号
    private AtomicInteger ticketNum = new AtomicInteger(); // 排队号

    public int lock() {
        // 首先原子性地获得一个排队号
        int myTicketNum = ticketNum.getAndIncrement();

        // 只要当前服务号不是自己的就不断轮询
        while (serviceNum.get() != myTicketNum) {
        }
        return myTicketNum;
    }

    public void unlock(int myTicket) {
        // 解锁后只有拥有该线程的下一个排队号线程才能加锁,保证了公平性,不会有插队的情况
        int next = myTicket + 1;
        // 只有当前线程拥有者才能释放锁
        serviceNum.compareAndSet(myTicket, next);
    }
}
