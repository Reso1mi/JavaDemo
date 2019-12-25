package advance_thread_study.Chapter8;

public class ImmutablePerformance {
    public static void main(String[] args) throws InterruptedException {
        long startTimestamp = System.currentTimeMillis();
        //SyncObj obj = new SyncObj(); 10991
        //obj.setName("LGW");
        ImmutableObj obj=new ImmutableObj("LGW");  //11674
        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (long l = 0L; l < 1000000; l++) {
                    System.out.println(Thread.currentThread().getName() + "=" + obj.toString());
                }
            }
        };
        t1.start();

        Thread t2 = new Thread() {
            @Override
            public void run() {
                for (long l = 0L; l < 1000000; l++) {
                    System.out.println(Thread.currentThread().getName() + "=" + obj.toString());
                }
            }
        };
        t2.start();
        t1.join();
        t2.join();


        long endTimestamp = System.currentTimeMillis();
        System.out.println("Elapsed time " + (endTimestamp - startTimestamp));
    }
}

class ImmutableObj {
    private final String name;

    public ImmutableObj(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ImmutableObj{" +
                "name='" + name + '\'' +
                '}';
    }
}

class SyncObj {
    private String name;

    public synchronized void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SyncObj{" +
                "name='" + name + '\'' +
                '}';
    }
}
