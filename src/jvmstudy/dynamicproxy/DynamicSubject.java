package jvmstudy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author imlgw.top
 * @date 2019/9/11 10:22
 */
public class DynamicSubject implements InvocationHandler {
    private Object subject;

    public DynamicSubject(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before calling "+method);
        Object invoke = method.invoke(this.subject, args);
        System.out.println("After calling "+method) ;
        return invoke;
    }

}
