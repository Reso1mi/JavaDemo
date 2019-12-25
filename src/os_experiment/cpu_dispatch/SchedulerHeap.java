package os_experiment.cpu_dispatch;

import java.util.*;

/**
 * @author imlgw.top
 * @date
 */
public class SchedulerHeap {

    //进程队列
    //private static LinkedList<Process> processList = new LinkedList<>();

    //采用最小堆
    private static Queue<Process> processQueue = new PriorityQueue<>(new Comparator<Process>() {
        @Override
        public int compare(Process o1, Process o2) {
            return o2.getPriority()-o1.getPriority();
        }
    });


    public static void main(String[] args) {
        SchedulerHeap schedulerHeap = new SchedulerHeap();
        //schedulerHeap.initPriorityProcess();
        schedulerHeap.initPriorityProcess2();
        //执行进程
        schedulerHeap.runProcess();
        //processQueue.peek().setPriority(-1);
        //schedulerHeap.practice2();
    }

    private void runProcess() {
        while (processQueue.peek() != null) {
            //取得队首元素执行（优先级最高的）
            run(processQueue.peek());
        }
    }

    private void practice1() {
        System.out.println(processQueue.peek());
    }

    private void practice2() {
        while (processQueue.peek() != null) {
            //取得队首元素执行（优先级最高的）
            run2(processQueue.peek());
            if(processQueue.size()==1){
                System.out.println(processQueue.peek());
            }
        }
    }

    //运行某一进程
    private void run(Process processHead) {
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
        //先移出队列
        processQueue.poll();
        if (processHead.getRequireTime() <= 0) {
            //设置end状态
            processHead.setState(Process.ProcesState.END);
            //移出队列（假移除）
            Optional.of("cur: " + processHead.getProcessName() + " end").ifPresent(System.err::println);
        } else {
            processQueue.add(processHead);
        }
        Optional.of("current processQueue# " + processQueue).ifPresent(System.out::println);
        System.out.println("------------------------------------");
    }

    private void run2(Process processHead) {
        //运行一次优先级减一
        processHead.setPriority(processHead.getPriority() - 1);
        //设置需要运行的时间减一
        processHead.setRequireTime(processHead.getRequireTime() - 1);
        //先移出队列
        processQueue.poll();
        if (processHead.getRequireTime() <= 0) {
            //设置end状态
            processHead.setState(Process.ProcesState.END);
        } else {
            processQueue.add(processHead);
        }
    }

    //初始化优先级调度
    private void initPriorityProcess() {
        //初始化5个进程
        Process process0 = new Process("Process-0", null, 5l, 6, Process.ProcesState.READY);
        Process process1 = new Process("Process-1", null, 4l, 7, Process.ProcesState.READY);
        Process process2 = new Process("Process-2", null, 4l, 8, Process.ProcesState.READY);
        Process process3 = new Process("Process-3", null, 5l, 9, Process.ProcesState.READY);
        Process process4 = new Process("Process-4", null, 6l, 10, Process.ProcesState.READY);
        //将进程添加到队列中
        processQueue.add(process0);
        processQueue.add(process1);
        processQueue.add(process2);
        processQueue.add(process3);
        processQueue.add(process4);
    }

    private void initPriorityProcess2() {
        Scanner scanner=new Scanner(System.in);
        int N = scanner.nextInt();
        for (int i = 0; i <N; i++) {
            processQueue.add(new Process(scanner.next(),null,scanner.nextInt(),scanner.nextInt(),Process.ProcesState.READY));
        }
    }
}
