package cn.zhixin.learn.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Zhixin Zhang
 * @Date: 2021/03/03/10:57 AM
 * @Description: 廖雪峰Java教程中，Java动态代理使用示范
 * Hello.class和hello.getClass()区别：
 * "类型名.class"被称为"Class Literal"，类字面量。
 * .class在编译时确定，getClass()在运行时根据实例确定。
 */
public class DynamicProxyLiao {

    public static void main(String[] args) {
        InvocationHandler handler = (proxy, method, args1) -> {
            System.out.println(method);
            if (method.getName().equals("sayHello")) {
                System.out.println("Good morning, " + args1[0]);
            }
            return null;
        };

        //Hello.class和Hello.getClass()的区别
        Hello hello = (Hello) Proxy.newProxyInstance(
                Hello.class.getClassLoader(), //传入ClassLoader
                new Class[]{Hello.class}, //传入要实现的接口
                handler); //传入处理调用方法的InvocationHandler

        hello.sayHello("Bob");
    }
}