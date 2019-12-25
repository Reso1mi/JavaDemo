package os_experiment.cpu_dispatch;


import java.util.LinkedList;
import java.util.Optional;
import java.util.Scanner;

public class Scheduler {

    //进程队列
    private static LinkedList<Process> processList = new LinkedList<>();

    private static Process processHead;

    private  static int count=0;

    public static void main(String[] args) {
        Scheduler Scheduler = new Scheduler();
        Scheduler.initPriorityProces2();
        processHead = sortList(processHead);
        //执行进程
        Scheduler.runProcess();
    }

    private void runProcess() {
        while (processHead != null) {
            System.out.println(++count);
            //取得队首元素执行（优先级最高的）
            run();
        }
    }


    private void run() {
        Optional.of("cur: " + processHead.getProcessName() + " running...").ifPresent(System.out::println);
        //睡眠模拟进程的运行
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //运行一次优先级减一
        processHead.setPriority(processHead.getPriority() - 1);
        //设置需要运行的时间减一
        processHead.setRequireTime(processHead.getRequireTime() - 1);

        if (processHead.getRequireTime() <= 0) {
            //设置end状态
            processHead.setState(Process.ProcesState.END);
            //移出队列（假移除）
            Optional.of("cur: " + processHead.getProcessName() + " end").ifPresent(System.err::println);
            processList.remove(processHead);
            processHead = processHead.nextProcess;
        } else {
            processHead = sortProcessList(processHead);
        }
        Optional.of("current processList# " + processList).ifPresent(System.out::println);
        System.out.println("------------------------------------");
    }


    private Process sortProcessList(Process head) {

        //下一个为空，或者大于下一个的优先级
        if (head.nextProcess== null || head.getPriority() > head.nextProcess.getPriority()) {
            return head;
        }
        Process temp = head.nextProcess;
        Process headP = temp;
        while (temp != null) {
            if (temp.nextProcess== null || (temp.nextProcess!= null && temp.getPriority() == head.getPriority() && temp.nextProcess.getPriority() < head.getPriority())) {
                head.nextProcess=temp.nextProcess;
                temp.nextProcess=head;
                break;
            }
            temp = temp.nextProcess;
        }
        return headP;
    }

    //归并排序
    public static Process sortList(Process head) {
        if(head.nextProcess==null) return head;
        Process fast = head;
        Process slow = head;
        Process pre=head;
        //快慢指针找中点
        while (fast!= null && fast.nextProcess!= null) {
            pre=slow;
            fast = fast.nextProcess.nextProcess;
            slow = slow.nextProcess;
        }
        pre.nextProcess=null;
        return merge2List(sortList(head),sortList(slow));
    }

    //merge两条链表
    public static Process merge2List(Process p1, Process p2) {
        if(p1==null) return p2;
        if(p2==null) return p1;
        Process dummyNode = new Process();
        Process temp=dummyNode;
        while (p1 != null && p2 != null) {
            if(p1.priority<=p2.priority){
                //相等的时候连接左边的，保证稳定性
                temp.nextProcess=p2;
                p2=p2.nextProcess;
            }else {
                temp.nextProcess=p1;
                p1=p1.nextProcess;
            }
            temp=temp.nextProcess;
        }
        temp.nextProcess=p1==null?p2:p1;
        return dummyNode.nextProcess;
    }

    //初始化优先级调度
    private void initPriorityProcess() {
        //初始化5个进程
        Process process0 = new Process("Process-0", null, 5l, 6, Process.ProcesState.READY);
        Process process1 = new Process("Process-1", null, 4l, 1, Process.ProcesState.READY);
        Process process2 = new Process("Process-2", null, 4l, 8, Process.ProcesState.READY);
        Process process3 = new Process("Process-3", null, 5l, -1, Process.ProcesState.READY);
        Process process4 = new Process("Process-4", null, 6l, 11, Process.ProcesState.READY);
        //连接链表
        process0.nextProcess=process1;
        process1.nextProcess=process2;
        process2.nextProcess=process3;
        process3.nextProcess=process4;
        //初始化头指针
        processHead = process0;
        processList.addLast(process0);
        processList.addLast(process1);
        processList.addLast(process2);
        processList.addLast(process3);
        processList.addLast(process4);
        sortList(processHead);
    }


    private void initPriorityProces2() {
        Scanner sc=new Scanner(System.in);
        int N = sc.nextInt();
        Process temp=null;
        for (int i = 0; i < N; i++) {
            temp =new Process(sc.next(),temp,sc.nextInt(),sc.nextInt(),Process.ProcesState.READY);
        }
        processHead=temp;
    }
}