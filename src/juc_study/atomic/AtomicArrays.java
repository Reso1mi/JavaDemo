package juc_study.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author imlgw.top
 * @date 2019/4/29 15:01
 */
public class AtomicArrays {
    public static void main(String[] args) {
        AtomicIntegerArray atomicIntegerArray=new AtomicIntegerArray(10);
        atomicIntegerArray.set(0,999);
        atomicIntegerArray.set(999,1);
        System.out.println(atomicIntegerArray.get(0));
    }
}
