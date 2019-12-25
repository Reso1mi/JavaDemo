package base_thread_study.chapter2;

public class Bank {
    public static void main(String[] args) {
        /*
        耦合方式
        TicketWindow t1=new TicketWindow("一号窗口");
        t1.start();
        TicketWindow t2=new TicketWindow("二号窗口");
        t2.start();
        TicketWindow t3=new TicketWindow("三号窗口");
        t3.start();
        */

        //线程和逻辑业务分离的写法
        TicketRunnable ticket=new TicketRunnable();
        Thread t1=new Thread(ticket,"一号窗口");
        Thread t2=new Thread(ticket,"二号窗口");
        Thread t3=new Thread(ticket,"三号窗口");
        t1.start();
        t2.start();
        t3.start();

        //策略模式 模拟线程start的方式
        /*TaxCalaculater tax = new TaxCalaculater(10000, 2000) {
            @Override
            protected double calcTax() {
                return getSalary()*0.1+getBonus();
            }
        };
        double taxs=tax.caculator();*/
        //TaxCalc就相当于runnable接口
        TaxCalaculater tax1 = new TaxCalaculater(10000, 2000, new TaxCalcWay() {
            @Override
            public double calcTax(double salary, double bonus) {
                return 0;
            }
        });
        tax1.caculator(); //tax1就像Thread

        //Strategy模式 jdk8的写法
        TaxCalaculater tax = new TaxCalaculater(10000, 2000, (salary, bonus) -> salary * 0.1 + bonus);
        double caculator = tax.caculator();
        System.out.println(caculator);
    }
}
