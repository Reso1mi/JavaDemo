package os_experiment.cpu_dispatch;

import java.util.Scanner;

/**
 * @author imlgw.top
 * @date 2019/4/24 13:12
 */
public class Practice1 {
    public static void main(String[] args) {
        Process[] processes=new Process[3];
        Scanner scanner = new Scanner(System.in);
        int maxIndex=0;
        for (int i = 0; i < 3; i++) {
            processes[i]=new Process(scanner.next(),scanner.nextInt(),scanner.nextInt());
            //大于
            maxIndex=processes[i].priority>processes[maxIndex].priority?i:maxIndex;
        }
        System.out.println(processes[maxIndex].processName);
    }

    static class Process {
        String processName;
        int priority;
        int time;

        public Process(String processName, int priority, int time) {
            this.processName = processName;
            this.priority = priority;
            this.time = time;
        }
    }
}

