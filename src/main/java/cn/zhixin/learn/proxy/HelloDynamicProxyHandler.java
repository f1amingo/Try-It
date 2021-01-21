package cn.zhixin.learn.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: Zhixin Zhang
 * @Date: 2021/01/21/9:29 PM
 * @Description: 利用java反射机制实现动态代理
 * 和静态代理（HelloStaticProxy）实现大体类似
 * 缺点：性能略差，但是更灵活
 * <p>
 * 参考：
 * 1. https://xie.infoq.cn/article/b70498d6c65595e0a3ccb4c7d
 */
public class HelloDynamicProxyHandler implements InvocationHandler {

    private Object target;

    public HelloDynamicProxyHandler(Object object) {
        this.target = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before...");
        //和HelloStaticProxy的主要区别
        //使用反射机制调用方法，这样对于任何类型，可以调用任何方法
        Object result = method.invoke(target, args);
        System.out.println("After...");
        //结果要返回
        return result;
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //对象还是得创建，实际干活的人不能少
        HelloImpl helloImpl = new HelloImpl();
        //获取到sayHello的方法签名，注意参数这里也要指明
        Method methodSayHello = helloImpl.getClass().getDeclaredMethod("sayHello", String.class);
        //指定对象，以及要调用的方法需要的参数，即可调用
        methodSayHello.invoke(helloImpl, "Zhang");
    }
}
