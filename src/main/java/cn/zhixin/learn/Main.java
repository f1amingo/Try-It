package cn.zhixin.learn;

import cn.zhixin.learn.proxy.Hello;
import cn.zhixin.learn.proxy.HelloImpl;
import cn.zhixin.learn.proxy.HelloStaticProxy;

public class Main {
    public static void main(String[] args) {
        Hello hello = new HelloImpl();
        hello.sayHello("World");

        HelloStaticProxy helloProxy = new HelloStaticProxy(new HelloImpl());
        helloProxy.sayHello("Zhang");

    }
}
