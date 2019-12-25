package juc_study.atomic;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author imlgw.top
 * @date 2019/4/23 16:32
 */
public class AtomicRefTest {

    public static void main(String[] args) {
        Simple simple = new Simple("imlgw.top", 19);
        Simple simple1 = new Simple("imlgw.top", 20);
        AtomicReference<Simple> atomicReference=new AtomicReference<>(simple);
        System.out.println(atomicReference.compareAndSet(simple, simple1));
        simple1.name="fix";
        System.out.println(atomicReference.compareAndSet(simple1, new Simple("dsaddasdasdsa", 12)));
        System.out.println("------------------------------------");
        //加了戳
        int[] holder = new int[2];
        AtomicStampedReference<Simple> atomicStampedReference=new AtomicStampedReference<>(simple,1);
        System.out.println(holder[0]);
        Simple reference = atomicStampedReference.getReference();
        System.out.println(simple==reference);

    }

    static class Simple{
        private String name;
        private int age;

        public Simple(String name, int age) {
            this.name= name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
