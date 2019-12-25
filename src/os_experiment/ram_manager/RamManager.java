package os_experiment.ram_manager;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author imlgw.top
 * @date 2019/4/19 13:39
 */
public class RamManager {
        public static void output(int[][] a) {
            int i, j;
            for (i = 0; i <= 7; i++) {
                for (j = 0; j <= 7; j++) {
                    System.out.print(a[i][j] + "   ");
                }
                System.out.println();
            }
        }


        public static int surplus(int[][] a) {
            int i, j;
            int num = 0;
            for (i = 0; i <= 7; i++) {
                for (j = 0; j <= 7; j++) {
                    if (a[i][j] == 0) {
                        num++;
                    }
                }
            }
            return num;
        }


        public static void apply_ram(int[][] a) {
            int i = 0, j = 0, k = 0;
            int id = 0;
            int residue = 0;
            int num = 0;                    //存放作业的内存数
            System.out.println("输入你想创建的作业id和作业需要的内存块：\n");
            Scanner scan = new Scanner(System.in);
            id = scan.nextInt();
            num = scan.nextInt();

            residue = surplus(a);
            if (residue < num) {
                System.out.println("没有足够的内存空间，创建进程失败！\n");
            } else {

                StoreClass sto = new StoreClass();
                sto.setId(id);

                int[] temp = new int[64];
                for (i = 0; i < 64; i++) {
                    temp[i] = -1;
                }
                sto.setYe(temp);

                boolean b = list.add(sto);
                for (i = 0; i <= 7; i++) {
                    for (j = 0; j <= 7; j++) {
                        if (a[i][j] == 0) {
                            a[i][j] = 1;
                            sto.ye[k] = i * 8 + j;
                            k++;
                        }
                        if (k == num) {//跳出循环
                            break;
                        }
                    }
                    if (k == num) {//跳出循环
                        break;
                    }
                }
            }
        }


        public static void revocation(int[][] a) {
            int tempi = -1, tempj = -1;
            int i = 0, j = 0, id = 0;
            String c = "";//表示是否删除作业
            int flag = 0;//作为表示是否查找到需要删除的作业的标志
            System.out.println("输入你想要删除的作业的id号：");
            Scanner scan = new Scanner(System.in);
            id = scan.nextInt();
            // Iterator<StoreClass> iter = list.iterator();
//        while (iter.hasNext()) {
            for (int k = 0; k < list.size(); k++) {
//            StoreClass store = iter.next();
                StoreClass store = (StoreClass) list.get(k);
                if (store.getId() == id) {
                    System.out.println("确定删除该作业吗？（y/n）");
                    c = scan.next();
                    if (c.equals("Y") || c.equals("y")) {
                        flag = 1;
                        list.remove(store);
                        for (i = 0; i <= 63; i++) {                     //将作业占用的内存块归还,将要撤销的进程是经过查找后的“m”.
                            if (store.ye[i] != -1) {                    //页表存放的数字是内存块的地址 （8*i+j）
                                tempi = store.ye[i] / 8;
                                tempj = store.ye[i] % 8;
                                a[tempi][tempj] = 0;                    //将内存块的使用标记修改为0
                            }
                        }
                    } else {
                        System.out.println("您取消了撤销该作业！");
                        return;
                    }
                }
            }
            if (flag != 1) {
                System.out.println("没有找到该作业，撤销作业失败！");
                return;
            }
        }
        //查看单个作业的页表信息

        public static void lookpage(int[][] a) {
            int i = 0, x = 0, y = 0;
            int id = 0;
            int flag = 0;//表示是否查找到该作业
            Scanner scan = new Scanner(System.in);
            System.out.println("输入你想要查看的作业的id号：");
            id = scan.nextInt();
            for (int k = 0; k < list.size(); k++) {
                StoreClass store = (StoreClass) list.get(k);
                if (store.getId() == id) {
                    flag = 1;
                    System.out.println("该作业的页表情况为：");
                    System.out.println("页号-----------块号---------- 详细");
                    for (i = 0; i <= 63; i++) {
                        if (store.ye[i] != -1) {
                            x = store.ye[i] / 8;
                            y = store.ye[i] % 8;
                            System.out.println(i + "---------------" + store.ye[i] + "-------------- 8 *" + x + "+" + y);
                        }
                    }
                    return;
                }
            }
            if (flag != 1) {
                System.out.println("没有查找到作业，查看失败！");
            }
        }

        public static LinkedList list = new LinkedList();


        public static void main(String[] args) {
            int num = 0;
            int order = 0;
            int[][] a = new int[8][8];                              //模拟内存，初始化为0
            for (int i = 0; i <= 7; i++) {
                for (int j = 0; j <= 7; j++) {
                    a[i][j] = 0;
                }
            }
            System.out.println("输入命令：\n0:退出    1:新建作业  2:撤销作业    3:查看页表      4：输出内存块\n");
            Scanner scan = new Scanner(System.in);
            order = scan.nextInt();
            while (order != 0) {
                switch (order) {
                    case 0:
                        break;
                    case 1:
                        apply_ram(a);
                        break;
                    case 2:
                        revocation(a);
                        break;
                    case 3:
                        lookpage(a);
                        break;
                    case 4:
                        output(a);
                        break;
                }
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println("输入命令：\n0:退出    1:新建作业   2:撤销作业    3:查看页表      4：输出内存块\n");
                order = scan.nextInt();
            }
            System.out.println("程序运行结束！\n");
        }
}
