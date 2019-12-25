package jvmstudy.class_executor;

import java.io.Serializable;

/**
 * @author imlgw.top
 * @date 2019/9/7 16:44
 */
public class OverloadTest {
/*
    public void test(char a) {
        System.out.println("char");
    }

    public void test(int a) {
        System.out.println("int");
    }

    public void test(long a) {
        System.out.println("long");
    }

    public void test(float a) {
        System.out.println("double");
    }

    public void test(double a) {
        System.out.println("double");
    }

    public void test(Character a) {
        System.out.println("Character");
    }

    public void test(Serializable serializable) {
        System.out.println("serializable");
    }

    public void test(char... a) {
        System.out.println("char[]");
    }*/

    public void test(Character... a) {
        System.out.println("char[]");
    }

    //不会执行
    public void test(Double a) {
        System.out.println("double");
    }

    public static void main(String[] args) {
        OverloadTest overloadTest = new OverloadTest();
        overloadTest.test('a');
    }
}
