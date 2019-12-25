package jvmstudy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author imlgw.top
 * @date 2019/9/11 10:25
 */
public class Client {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        RealSubject realSubject=new RealSubject();

        InvocationHandler invocationHandler=new DynamicSubject(realSubject);

        Class<? extends RealSubject> subClass = realSubject.getClass();
        //生成代理类
        //param0 类加载器
        //param1 委托类的接口
        //param2 调用处理器 DynamicSubject
        Subject subject= (Subject) Proxy.newProxyInstance(subClass.getClassLoader(),subClass.getInterfaces(),invocationHandler);
        //调用代理类的request方法
        /*
            Before calling public abstract void jvmstudy.dynamicproxy.Subject.request()
            real Subject
            After calling public abstract void jvmstudy.dynamicproxy.Subject.request()
        */
        subject.request();

        System.out.println(subject.getClass()); //class com.sun.proxy.$Proxy0
        System.out.println(subject.getClass().getSuperclass()); //class java.lang.reflect.Proxy

        System.out.println("-----------------");

        /*
            Before calling public java.lang.String java.lang.Object.toString()
            After calling public java.lang.String java.lang.Object.toString()
        */
        subject.toString(); //这个方法也会被代理

        System.out.println("-----------------");

        /*
            Before calling public native int java.lang.Object.hashCode()
            After calling public native int java.lang.Object.hashCode()
            692404036
        */
        System.out.println(subject.hashCode()); //也会被代理
    }
}
