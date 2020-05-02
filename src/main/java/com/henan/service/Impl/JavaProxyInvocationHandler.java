package com.henan.service.Impl;


import com.henan.service.IHelloService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// 中间类
public class JavaProxyInvocationHandler implements InvocationHandler {
    /**
     * 中间类持有委托类对象的引用,这里会构成一种静态代理关系
     */
    private Object obj ;
    /**
     * 有参构造器,传入委托类的对象
     * @param obj 委托类的对象
     */
    public JavaProxyInvocationHandler(Object obj){
        this.obj = obj;
    }
    /**
     * 动态生成代理类对象,Proxy.newProxyInstance
     * @return 返回代理类的实例
     */
    public Object newProxyInstance() {
        return Proxy.newProxyInstance(
                //指定代理对象的类加载器
                obj.getClass().getClassLoader(),
                //代理对象需要实现的接口，可以同时指定多个接口
                obj.getClass().getInterfaces(),
                //方法调用的实际处理者，代理对象的方法调用都会转发到这里
                this);
    }
    /**
     *
     * @param proxy 代理对象
     * @param method 代理方法
     * @param args 方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws
            Throwable {
        System.out.println("invoke before");
        Object result = method.invoke(obj, args);
        System.out.println("invoke after");
        return result;
    }
}
// 测试动态代理类
class MainJavaProxy {
    public static void main(String[] args) {
        JavaProxyInvocationHandler proxyInvocationHandler = new
                JavaProxyInvocationHandler(new HelloService());
        IHelloService helloService = (IHelloService)
                proxyInvocationHandler.newProxyInstance();
        helloService.sayByeBye("ttt");
        helloService.sayHello("www");
    }
}
//JDK 动态代理所用到的代理类在程序调用到代理类对象时才由 JVM 真正创建，JVM根据传进来的
//业务实现类对象 以及 方法名 ，动态地创建了一个代理类的 class 文件并被字节码 引擎执行，然后
//通过该代理类对象进行方法调用。我们需要做的，只需指定代理类的预处理、调用后操作即可。

