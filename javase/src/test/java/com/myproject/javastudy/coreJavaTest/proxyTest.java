package com.myproject.javastudy.coreJavaTest;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author lkxl
 */
public class proxyTest {
    @Test
    void testProxy(){
        targetInterface targetOf=new target();
        targetInterface proxy = (targetInterface)Proxy.newProxyInstance(targetOf.getClass().getClassLoader(),
                targetOf.getClass().getInterfaces(),
                (object, method, args)->{
                    System.out.println("before");
                    Object result = method.invoke(targetOf, args);
                    System.out.println("after");
                    return result;});
        proxy.say();
    }

}
interface targetInterface{
    public void say();
}
class target implements targetInterface{
    @Override
    public void say(){
        System.out.println("hello");
    }
}

class Handler implements InvocationHandler {
    private targetInterface target;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object result = method.invoke(target, args);
        System.out.println("after");
        return result;
    }
    public Handler(targetInterface target) {
        this.target = target;
    }
}
