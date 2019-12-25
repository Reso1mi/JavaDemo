package juc_study.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author imlgw.top
 * @date 2019/4/22 22:20
 */
public class AtomicBooleanTest {
    public static void main(String[] args) {
        AtomicBoolean bool=new AtomicBoolean();
        System.out.println(bool.get()); //false
    }
}
